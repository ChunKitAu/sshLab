package com.ssh.auth.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.ssh.auth.dao.UserDAO;
import com.ssh.auth.domain.User;
import com.ssh.auth.service.UserService;
import com.ssh.common.bean.CommonResult;
import com.ssh.common.bean.PageBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Resource(name="userDAO")
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public CommonResult save(User user) {
        Integer save = userDAO.save(user);
        if(save > 0)
            return CommonResult.success();
        else
            return CommonResult.fail();
    }

    @Override
    public CommonResult deleteByUserId(Integer userId) {
        Integer integer = userDAO.deleteByUserId(userId);
        if(integer > 0){
            return CommonResult.success();
        }else return CommonResult.fail();
    }

    @Override
    public CommonResult update(User user) {
        userDAO.update(user);
        return CommonResult.success();
    }

    @Override
    public CommonResult getUsers(Integer currentPage,Integer pageSize) {
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        //设置记录总数
        pageBean.setTotal(userDAO.getCount());
        //获取总页数
         pageBean.setTotalPage( pageBean.getTotal() % pageSize == 0 ? pageBean.getTotal() / pageSize : pageBean.getTotal() / pageSize +1);


        //页数超了
        if(currentPage <= 1)
            pageBean.setCurrPage(1);
        else if(currentPage > pageBean.getTotalPage())
            //设置当前页为最后一页
            pageBean.setCurrPage(pageBean.getTotalPage());
        else //没超
            pageBean.setCurrPage(currentPage);

        pageBean = userDAO.getAll(pageBean);

        return CommonResult.success(pageBean);
    }

    @Override
    public CommonResult getUserByUserId(Integer userId) {
        User user =  userDAO.getOneByUserId(userId);
        if(user != null){
            return CommonResult.success(user);
        }
        else {
            //重写返回信息
            CommonResult result = CommonResult.fail();
            result.setMessage("当前用户不存在");
            return result;
        }
    }

    @Override
    public CommonResult login(User user) {
        //用于标记用户是否在一天内登陆
        boolean  flag = false;
        User tokenUser = userDAO.getUserByAccountNameAndPassword(user);
        if(tokenUser != null){
            //第一次登陆
            if(tokenUser.getLoginTime() == null ){
                flag = true;
            } else {  //若登陆时间为第二天
                int loginDay = tokenUser.getLoginTime().getDate();
                int nowDay = new Date().getDate();
                if(nowDay - loginDay >= 1){
                    flag = true;
                }
            }
            //获取每天积分
            if(flag){
                tokenUser.setLoginTime(new Date());
                tokenUser.setIntegral(tokenUser.getIntegral()+10);
                userDAO.update(tokenUser);
            }
            //防止密码被盗用
            tokenUser.setPassword(null);
            //自定义生成token
            //生成密钥
            String code64= Base64.encode(JSONUtil.toJsonStr(tokenUser), "utf-8");

            return CommonResult.success(code64);
        }else {
            CommonResult result = CommonResult.fail();
            result.setMessage("账号或密码错误！");
            return result;
        }
    }
}
