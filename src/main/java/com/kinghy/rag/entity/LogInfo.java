package com.kinghy.rag.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName log_info
 */
@TableName(value ="log_info")
@Data
public class LogInfo {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 类目
     */
    private String className;

    /**
     * 请求时间戳
     */
    private Date requestTime;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String response;
}