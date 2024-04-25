package com.mlv.learn.api;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlv.learn.common.Result;
import com.mlv.learn.pojo.TaskDataFill;
import com.mlv.learn.service.TaskDataFillService;
import com.mlv.learn.vo.TrendDataVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TaskDataFill)表控制层
 *
 * @author xiaolv
 * @since 2024-04-16 21:04:06
 */
@RestController
@RequestMapping("taskDataFill")
public class TaskDataFillApi {
    /**
     * 服务对象
     */
    @Resource
    private TaskDataFillService taskDataFillService;

//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param taskDataFill 查询实体
//     * @return 所有数据
//     */
//    @GetMapping("/list")
//    public Result<Object> selectAll(Page<TaskDataFill> page, TaskDataFill taskDataFill) {
//        return new Result<>().success(this.taskDataFillService.page(page, new QueryWrapper<>(taskDataFill)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("/getInfoById")
//    public Result<Object> selectOne(@RequestParam("id") String id) {
//        return new Result<>().success(this.taskDataFillService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param taskDataFill 实体对象
//     * @return 新增结果
//     */
//    @PostMapping("/add")
//    public Result<Object> insert(@RequestBody TaskDataFill taskDataFill) {
//        return new Result<>().success(this.taskDataFillService.save(taskDataFill));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param taskDataFill 实体对象
//     * @return 修改结果
//     */
//    @PostMapping("/edit")
//    public Result<Object> update(@RequestBody TaskDataFill taskDataFill) {
//        return new Result<>().success(this.taskDataFillService.updateById(taskDataFill));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id 主键结合
//     * @return 删除结果
//     */
//    @PostMapping("/del")
//    public Result<Object> delete(@RequestParam("id") String id) {
//        return new Result<>().success(this.taskDataFillService.removeById(id));
//    }

    /**
     * 获取趋势图数据
     * @param query
     * @return
     */
    @PostMapping("/getTrendData")
    public Result<Object> getTrendData(@RequestBody TrendDataVO query) {
        return new Result<>().success(this.taskDataFillService.getTrendData(query));
    }
}

