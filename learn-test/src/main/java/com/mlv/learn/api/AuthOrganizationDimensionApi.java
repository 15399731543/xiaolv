package com.mlv.learn.api;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlv.learn.common.Result;
import com.mlv.learn.pojo.AuthOrganizationDimension;
import com.mlv.learn.service.AuthOrganizationDimensionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 组织机构维度(AuthOrganizationDimension)表控制层
 *
 * @author xiaolv
 * @since 2024-04-15 21:09:33
 */
@RestController
@RequestMapping("authOrganizationDimension")
public class AuthOrganizationDimensionApi {
    /**
     * 服务对象
     */
    @Resource
    private AuthOrganizationDimensionService authOrganizationDimensionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param authOrganizationDimension 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<Object> selectAll(Page<AuthOrganizationDimension> page, AuthOrganizationDimension authOrganizationDimension) {
        return new Result<>().success(this.authOrganizationDimensionService.page(page, new QueryWrapper<>(authOrganizationDimension)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getInfoById")
    public Result<Object> selectOne(@RequestParam("id") String id) {
        return new Result<>().success(this.authOrganizationDimensionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param authOrganizationDimension 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<Object> insert(@RequestBody AuthOrganizationDimension authOrganizationDimension) {
        return new Result<>().success(this.authOrganizationDimensionService.save(authOrganizationDimension));
    }

    /**
     * 修改数据
     *
     * @param authOrganizationDimension 实体对象
     * @return 修改结果
     */
    @PostMapping("/edit")
    public Result<Object> update(@RequestBody AuthOrganizationDimension authOrganizationDimension) {
        return new Result<>().success(this.authOrganizationDimensionService.updateById(authOrganizationDimension));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @PostMapping("/del")
    public Result<Object> delete(@RequestParam("id") String id) {
        return new Result<>().success(this.authOrganizationDimensionService.removeById(id));
    }
}

