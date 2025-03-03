package com.kinghy.rag.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName sensitive_word
 */
@TableName(value ="sensitive_word")
@Data
public class SensitiveWord {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 敏感词内容
     */
    private String word;

    /**
     * 敏感词类别
     */
    private String category;

    /**
     * 敏感词状态
     */
    private String status;

    /**
     * 创建时间戳
     */
    private String createdAt;

    /**
     * 更新时间戳
     */
    private String updatedAt;
}