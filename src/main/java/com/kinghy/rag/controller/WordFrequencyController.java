package com.kinghy.rag.controller;

import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.common.PageResult;
import com.kinghy.rag.common.ResultUtils;
import com.kinghy.rag.pojo.dto.WordFrequencyPageQueryDTO;
import com.kinghy.rag.service.WordFrequencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: WordFrequencyController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/3/7 15:22
 * @description: 分词统计控制器
 */

@Tag(name="WordFrequencyController",description = "分词统计控制器")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/frequency")
public class WordFrequencyController {
    @Autowired
    private WordFrequencyService wordFrequencyService;

    // 分页条件查询
    @PostMapping("/page")
    @Operation(summary = "page", description = "分页查询")
    public BaseResponse<PageResult> pageQuery(@RequestBody WordFrequencyPageQueryDTO queryDTO) {
        PageResult pageResult = wordFrequencyService.pageQuery(queryDTO);
        return ResultUtils.success(pageResult);
    }

    // 清空数据
    @DeleteMapping("/clean")
    @Operation(summary = "clean", description = "清空数据")
    public BaseResponse<String> clean() {
        wordFrequencyService.remove(null);
        return ResultUtils.success("清空成功");
    }


}
