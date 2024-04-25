package  com.mlv.learn.api;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlv.learn.common.Result;
import com.mlv.learn.pojo.SpecialActionEnterprises;
import com.mlv.learn.service.SpecialActionEnterprisesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (JkztuvSpecialActionEnterprises)表控制层
 *
 * @author xiaolv
 * @since 2024-04-15 21:14:05
 */
@RestController
@RequestMapping("specialActionEnterprises")
public class SpecialActionEnterprisesApi {
    /**
     * 服务对象
     */
    @Resource
    private SpecialActionEnterprisesService specialActionEnterprisesService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param specialActionEnterprises 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<Object> selectAll(Page<SpecialActionEnterprises> page, SpecialActionEnterprises specialActionEnterprises) {
        return new Result<>().success(this.specialActionEnterprisesService.page(page, new QueryWrapper<>(specialActionEnterprises)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getInfoById")
    public Result<Object> selectOne(@RequestParam("id") String id) {
        return new Result<>().success(this.specialActionEnterprisesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param specialActionEnterprises 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<Object> insert(@RequestBody SpecialActionEnterprises specialActionEnterprises) {
        return new Result<>().success(this.specialActionEnterprisesService.save(specialActionEnterprises));
    }

    /**
     * 修改数据
     *
     * @param specialActionEnterprises 实体对象
     * @return 修改结果
     */
    @PostMapping("/edit")
    public Result<Object> update(@RequestBody SpecialActionEnterprises specialActionEnterprises) {
        return new Result<>().success(this.specialActionEnterprisesService.updateById(specialActionEnterprises));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @PostMapping("/del")
    public Result<Object> delete(@RequestParam("id") String id) {
        return new Result<>().success(this.specialActionEnterprisesService.removeById(id));
    }
}

