package com.ssh.auth.service;

import com.ssh.auth.domain.User;
import com.ssh.common.bean.CommonResult;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    public CommonResult save(User user);


    /**
     * 根据用户id删除用户
     * @param userId
     * @return
     */
    public CommonResult deleteByUserId(Integer userId);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public CommonResult update(User user);

    /**
     * 获取全部用户
     * @return
     */
    public CommonResult getUsers(Integer currentPage,Integer pageSize);


    /**
     * 根据用户id获取用户信息
     * @return
     */
    public CommonResult getUserByUserId(Integer userId);


    public CommonResult login(User user);

}
