package com.ssh.auth.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.auth.domain.User;
import com.ssh.auth.service.UserService;
import com.ssh.common.bean.CommonResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
public class UserAction extends ActionSupport {

    private User user;

    private UserService userService;

    private User loginUser;

    //用于数据返回格式化
    private CommonResult result = null;
    private CommonResult authResult = CommonResult.authFail();
    //用于验证错误信息收集
    private List<String> error = new ArrayList<>();

    //当前页
    private Integer currentPage;
    //显示的页数
    private Integer pageSize;

    public String save(){
        //清空错误信息
        error.clear();
        if(user.getAccountName() == null || user.getAccountName() == "")
            error.add("账户名不能为空");

        if(user.getRoleId() == null)
            error.add("用户角色不能为空");

        if(user.getPassword()  == null || user.getPassword() == "")
            error.add("密码不能为空");

        if(user.getUserName()  == null || user.getUserName() == "")
            error.add("用户名不能为空");

        //表单验证失败
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }

        //新建的用户的积分为0
        user.setIntegral(0);
        result = userService.save(user);
        return  SUCCESS;
    }





    public String deleteByUserId(){
        error.clear();
        if(user.getId() == null){
            error.add("用户id不能为空");
            result = CommonResult.validateFail(error);
        }
        result = userService.deleteByUserId(user.getId());
        return SUCCESS;
    }

    public String update(){
        //清空错误信息
        error.clear();
        if(user.getId() == null)
            error.add("用户id不能为空");

        if(user.getAccountName() == null || user.getAccountName() == "")
            error.add("账户名不能为空");

        if(user.getRoleId() == null)
            error.add("用户角色不能为空");

        if(user.getPassword()  == null || user.getPassword() == "")
            error.add("密码不能为空");

        if(user.getUserName()  == null || user.getUserName() == "")
            error.add("用户名不能为空");

        //表单验证失败
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = userService.update(user);
        return  SUCCESS;
    }

    public String getAll() {
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result =  userService.getUsers(currentPage ,pageSize);
        return SUCCESS;
    }



    public String login(){
        if(user.getAccountName() == null || user.getAccountName() == "")
            error.add("账号不能为空");
        if(user.getPassword() == null || user.getPassword() == "")
            error.add("密码不能为空");
        result = userService.login(user);
        return SUCCESS;
    }


    public String getOneByUserId(){
        error.clear();
        if(user.getId() == null){
            error.add("用户id不能为空");
            result = CommonResult.validateFail(error);
        }
        result = userService.getUserByUserId(user.getId());
        return SUCCESS;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }


    public void setResult(CommonResult result) {
        this.result = result;
    }

    public CommonResult getResult() {
        return result;
    }


    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CommonResult getAuthResult() {
        return authResult;
    }
}
