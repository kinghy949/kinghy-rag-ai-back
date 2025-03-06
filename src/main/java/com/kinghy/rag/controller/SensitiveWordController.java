package com.kinghy.rag.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.common.ResultUtils;
import com.kinghy.rag.entity.SensitiveWord;
import com.kinghy.rag.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Title: SenSitiveWordController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/3/3 21:30
 * @description: 敏感词控制器
 */

@Tag(name = "SensitiveWordController", description = "敏感词控制器")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/sensitive")
public class SensitiveWordController {

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Operation(summary = "新增敏感词")
    @PostMapping("/add")
    public BaseResponse addSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        log.info("新增敏感词：{}", sensitiveWord);
        sensitiveWord.setStatus("1");
        sensitiveWord.setCreatedAt(LocalDate.now().toString());
        sensitiveWord.setUpdatedAt(LocalDate.now().toString());
        boolean save = sensitiveWordService.save(sensitiveWord);
        if (save){
            return ResultUtils.success(true);
        }
        return ResultUtils.error("新增失败");
    }

    @Operation(summary = "删除敏感词")
    @DeleteMapping("/{id}")
    public boolean deleteSensitiveWord(@PathVariable Integer id) {
        return sensitiveWordService.removeById(id);
    }

    @Operation(summary = "批量删除敏感词")
    @PostMapping("/batch")
    public BaseResponse deleteSensitiveWords(@RequestBody List<Integer> ids) {
        boolean b = sensitiveWordService.removeByIds(ids);
        if (b){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Operation(summary = "更新敏感词")
    @PutMapping
    public boolean updateSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        return sensitiveWordService.updateById(sensitiveWord);
    }

    @Operation(summary = "分页查询敏感词")
    @GetMapping("/page")
    public BaseResponse<IPage<SensitiveWord>> getSensitiveWordPage(@RequestParam int page, @RequestParam int size) {
        Page<SensitiveWord> pageParam = new Page<>(page, size);
        Page<SensitiveWord> page1 = sensitiveWordService.page(pageParam);
        page1.setTotal(page1.getRecords().size());
        return ResultUtils.success(page1);
    }

    @Operation(summary = "查询所有敏感词")
    @GetMapping
    public List<SensitiveWord> getAllSensitiveWords() {
        return sensitiveWordService.list();
    }


}
