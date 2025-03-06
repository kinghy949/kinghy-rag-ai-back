package com.kinghy.rag.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName word_frequency
 */
@TableName(value ="word_frequency")
@Data
public class WordFrequency {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 分词
     */
    private String word;

    /**
     * 出现频次
     */
    private Integer countNum;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}