package com.kinghy.rag.service;

import com.kinghy.rag.common.PageResult;
import com.kinghy.rag.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinghy.rag.pojo.dto.UserDTO;
import com.kinghy.rag.pojo.dto.UserPageQueryDTO;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;

/**
* @author kinghy
* @description 针对表【user】的数据库操作Service
* @createDate 2025-02-14 21:05:04
*/
public interface UserService extends IService<User> {

    User login(String userName, String password) throws AccountNotFoundException, AccountLockedException;

    void saveUser(UserDTO userDTO);

    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    void startOrStop(Integer status, Long id);

    void updateUser(UserDTO userDTO);
}
