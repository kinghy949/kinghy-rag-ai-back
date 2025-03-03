package com.kinghy.rag.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.entity.SensitiveWord;
import com.kinghy.rag.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public boolean addSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        return sensitiveWordService.save(sensitiveWord);
    }

    @Operation(summary = "删除敏感词")
    @DeleteMapping("/{id}")
    public boolean deleteSensitiveWord(@PathVariable Integer id) {
        return sensitiveWordService.removeById(id);
    }

    @Operation(summary = "更新敏感词")
    @PutMapping
    public boolean updateSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        return sensitiveWordService.updateById(sensitiveWord);
    }

    @Operation(summary = "分页查询敏感词")
    @GetMapping("/page")
    public IPage<SensitiveWord> getSensitiveWordPage(@RequestParam int page, @RequestParam int size) {
        Page<SensitiveWord> pageParam = new Page<>(page, size);
        return sensitiveWordService.page(pageParam);
    }

    @Operation(summary = "查询所有敏感词")
    @GetMapping
    public List<SensitiveWord> getAllSensitiveWords() {
        return sensitiveWordService.list();
    }


}
