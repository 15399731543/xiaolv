package com.mlv.learn.api;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlv.learn.common.Result;
import com.mlv.learn.pojo.AuthOrganization;
import com.mlv.learn.service.AuthOrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织机构表  (AuthOrganization)表控制层
 *
 * @author xiaolv
 * @since 2024-04-15 20:21:42
 */
@RestController
@RequestMapping("authOrganization")
public class AuthOrganizationApi {
    /**
     * 服务对象
     */
    @Resource
    private AuthOrganizationService authOrganizationService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param authOrganization 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result selectAll(Page<AuthOrganization> page, AuthOrganization authOrganization) {
        return new Result().success(this.authOrganizationService.page(page, new QueryWrapper<>(authOrganization)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getInfoById")
    public Result<Object> selectOne(@RequestParam("id") String id) {
        return new Result<>().success(this.authOrganizationService.getById(id));
    }

    /**
     * 导出数据
     * @return
     */
    @GetMapping("/export")
    public Result<Object> export() {
        List<AuthOrganization> authOrganizations = this.authOrganizationService.exportAuthOrganization();
        // 指定列导出
        String column = "studentName,age,phone";// 定义无需导出的列字段

        return new Result<>().success(null);
    }
    /**
     * 新增数据
     *
     * @param authOrganization 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<Object> insert(@RequestBody AuthOrganization authOrganization) {
        return new Result<>().success(this.authOrganizationService.save(authOrganization));
    }

    /**
     * 修改数据
     *
     * @param authOrganization 实体对象
     * @return 修改结果
     */
    @PostMapping("/edit")
    public Result<Object> update(@RequestBody AuthOrganization authOrganization) {
        return new Result<>().success(this.authOrganizationService.updateById(authOrganization));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @PostMapping("/del")
    public Result<Object> delete(@RequestParam("id") String id) {
        return new Result<>().success(this.authOrganizationService.removeById(id));
    }
    /**
     * 获取所有组织机构树
     * @return
     */
    @GetMapping("/getAllOrg")
    public Result<Object> getAllOrg() {
        return new Result<>().success(this.authOrganizationService.getAllOrg());
    }
}

