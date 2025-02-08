package com.kinghy.rag.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName test
 */
@TableName(value ="test")
@Data
public class Test implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}