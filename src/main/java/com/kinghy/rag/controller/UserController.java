package com.kinghy.rag.controller;

import com.kinghy.rag.common.ApplicationConstant;
import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.common.PageResult;
import com.kinghy.rag.common.ResultUtils;
import com.kinghy.rag.constant.JwtClaimsConstant;
import com.kinghy.rag.entity.User;
import com.kinghy.rag.pojo.dto.UserDTO;
import com.kinghy.rag.pojo.dto.UserPageQueryDTO;
import com.kinghy.rag.pojo.vo.UserLoginVO;
import com.kinghy.rag.config.JwtProperties;
import com.kinghy.rag.service.UserService;
import com.kinghy.rag.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: UserController
 * @Author KingHY
 * @Package com.kinghy.rag.controller
 * @Date 2025/2/14 21:06
 * @description: 用户控制层
 */

@Tag(name="UserController",description = "用户管理")
@Slf4j
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 注册
     *
     */
    @PostMapping("/register")
    @Operation(summary = "register",description = "注册")
    public BaseResponse register(@RequestBody User user) {
        log.info("注册：{}", user.toString());

        if (userService.getByUsername(user.getUserName())) {
            return ResultUtils.error("用户名已存在");
        }else {
            userService.register(user);
        }
        return ResultUtils.success("注册成功");
    }


    /**
     * 登录
     *
     * @param
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "login",description = "登录")
    public BaseResponse login(@RequestParam(value = "userName", defaultValue = "admin") String userName,
                                               @RequestParam(value = "password", defaultValue = "123456") String password) throws AccountLockedException, AccountNotFoundException {
        log.info("登录：{}", userName+":"+password);

        User user = userService.login(userName, password);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .name(user.getName())
                .token(token)
                .build();

        return ResultUtils.success(userLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "logout",description = "退出")
    public BaseResponse<String> logout() {
        return ResultUtils.success("退出成功");
    }

    /**
     * 新增
     * @param userDTO
     * @return
     */
    @PostMapping("/addUser")
    @Operation(summary = "logout",description = "新增user")
    public BaseResponse save(@RequestBody UserDTO userDTO){
        log.info("新增员工：{}",userDTO);
        userService.saveUser(userDTO);
        return ResultUtils.success("新增成功");
    }

    /**
     * 员工分页查询
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "page",description = "user分页查询")
    public BaseResponse<PageResult> page(UserPageQueryDTO userPageQueryDTO){
        log.info("员工分页查询，参数为：{}", userPageQueryDTO);
        PageResult pageResult = userService.pageQuery(userPageQueryDTO);
        return ResultUtils.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "status",description = "启用禁用账号")
    public BaseResponse startOrStop(@PathVariable Integer status,Integer id){
        log.info("启用禁用员工账号：{},{}",status,id);
        userService.startOrStop(status,id);
        return ResultUtils.success("禁用成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "info",description = "根据id查询user信息")
    public BaseResponse<User> getById(@PathVariable Long id){
        User employee = userService.getById(id);
        return ResultUtils.success(employee);
    }

    /**
     * 编辑员工信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    @Operation(summary = "info",description = "编辑user信息")
    public BaseResponse update(@RequestBody User user){
        log.info("编辑员工信息：{}", user);
        userService.updateById(user);
        return ResultUtils.success("编辑成功");
    }
}
