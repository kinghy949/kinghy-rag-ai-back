package com.kinghy.rag.pojo.dto;

import lombok.Data;

/**
 * @Title: QueryFileDTO
 * @Author KingHY
 * @Package com.kinghy.rag.pojo.dto
 * @Date 2025/2/8 21:31
 * @description: 查找文件dto
 */
@Data
public class QueryFileDTO {
    private Integer page;
    private Integer pageSize;
    private String fileName;
}
