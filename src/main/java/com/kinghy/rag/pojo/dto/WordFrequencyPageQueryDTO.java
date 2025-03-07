package com.kinghy.rag.pojo.dto;

import lombok.Data;

@Data
public class WordFrequencyPageQueryDTO {
    private int page;
    private int pageSize;
    private String word;
    private String businessType;
    private Integer countNumMin;
}