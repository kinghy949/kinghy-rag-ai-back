package com.kinghy.rag.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName sensitive_category
 */
@TableName(value ="sensitive_category")
@Data
public class SensitiveCategory {
    /**
     * 主键ID
     */
    @TableId
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private LocalDate createdTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 状态
     */
    private String status;
}