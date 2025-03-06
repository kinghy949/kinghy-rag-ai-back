package com.kinghy.rag.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.common.ResultUtils;
import com.kinghy.rag.entity.LogInfo;
import com.kinghy.rag.service.LogInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: LogInfoController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/3/3 22:03
 * @description: 日志控制器
 */
@Tag(name = "LogInfoController", description = "日志控制器")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/log")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    @Operation(summary = "分页查询日志信息（带条件查询）")
    @GetMapping("/page")
    public BaseResponse<IPage<LogInfo>> getLogInfoPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String requestParams) {
        Page<LogInfo> pageParam = new Page<>(page, size);
        QueryWrapper<LogInfo> queryWrapper = new QueryWrapper<>();
        if (methodName != null) {
            queryWrapper.like("method_name", methodName);
        }
        if (className != null) {
            queryWrapper.like("class_name", className);
        }
        if (requestParams != null) {
            queryWrapper.like("request_params", requestParams);
        }
        Page<LogInfo> result = logInfoService.page(pageParam, queryWrapper);
        result.setTotal(result.getRecords().size());
        return ResultUtils.success(result);
    }

    @Operation(summary = "批量删除日志信息")
    @PostMapping("/batch")
    public BaseResponse deleteLogInfos() {
        boolean result = logInfoService.remove(null);
        if (result) {
            return ResultUtils.success("删除成功");
        } else {
            return ResultUtils.error("删除失败");
        }
    }
}
