package com.kinghy.rag.pojo.dto;

import lombok.Data;

/**
 * @Title: PasswardDTO
 * @Author KingHY
 * @Package com.kinghy.rag.pojo.dto
 * @Date 2025/3/26 19:02
 * @description: 修改密码DTO
 */

@Data
public class PasswordDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
