package com.bob.frame.bobframeprojecta.controller;

import com.bob.common.entity.CommonResult;
import com.bob.common.enums.FrameExceptionEnum;
import com.bob.common.exception.FrameException;
import com.bob.frame.bobframeprojecta.entity.User;
import com.bob.frame.bobframeprojecta.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-10 20:22:07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/info/{id}")
    public CommonResult<User> selectOne(@PathVariable(value = "id") Integer id) {
        User user = this.userService.queryById(id);
        return CommonResult.success(user);
    }

    @GetMapping("/error")
    public CommonResult errorTest() {
        throw new FrameException(FrameExceptionEnum.USER_NOT_EXIST);
    }

}