package com.kinghy.rag.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.common.ResultUtils;
import com.kinghy.rag.entity.SensitiveCategory;
import com.kinghy.rag.service.SensitiveCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @Title: SensitiveCategoryController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/3/18 0:16
 * @description: 敏感词分类控制器
 */



@Tag(name = "SensitiveCategoryController", description = "敏感词分类控制器")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/category")
public class SensitiveCategoryController {
    @Autowired
    private SensitiveCategoryService sensitiveCategoryService;

    // 新增接口
    @Operation(summary = "新增敏感词分类")
    @PostMapping("/add")
    public BaseResponse<Boolean> create(@RequestBody SensitiveCategory entity) {
        entity.setCreatedTime(LocalDate.now());
        entity.setUpdateTime(LocalDate.now());
        entity.setStatus("1");
        return ResultUtils.success(sensitiveCategoryService.save(entity));
    }

    // 批量删除接口
    @Operation(summary = "批量删除")
    @DeleteMapping("/batch")
    public BaseResponse<Boolean> batchDelete(@RequestBody List<Integer> ids) {
        return ResultUtils.success(sensitiveCategoryService.removeByIds(ids));
    }

    // 修改接口
    @Operation(summary = "修改敏感词")
    @PutMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody SensitiveCategory entity) {
        entity.setUpdateTime(LocalDate.now());
        return ResultUtils.success(sensitiveCategoryService.updateById(entity));
    }

    // 分页查询接口
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public BaseResponse<IPage<SensitiveCategory>> page(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<SensitiveCategory> pageParam = new Page<>(page, size);
        Page<SensitiveCategory> pages = sensitiveCategoryService.page(pageParam);
        pages.setTotal(pages.getRecords().size());
        return ResultUtils.success(pages);
    }

    // 列表查询接口
    @Operation(summary = "获取全部列表")
    @GetMapping("/list")
    public BaseResponse<List<SensitiveCategory>> list() {
        return ResultUtils.success(sensitiveCategoryService.list());
    }



}
