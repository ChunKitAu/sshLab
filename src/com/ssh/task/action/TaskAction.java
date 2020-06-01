package com.ssh.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.auth.domain.User;
import com.ssh.task.domain.Task;
import com.ssh.task.service.TaskService;
import com.ssh.common.bean.CommonResult;
import com.ssh.task.dao.TaskDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskAction extends ActionSupport {
    private Task task;
    private TaskService taskService;


    private User user;
    private User loginUser;

    //用于数据返回格式化
    private CommonResult result = null;
    //用于验证错误信息收集
    private List<String> error = new ArrayList<>();
    private CommonResult authResult = CommonResult.authFail();
    //当前页
    private Integer currentPage;
    //显示的页数
    private Integer pageSize;

    private boolean check(String str,String name){
        if(str==null ||str=="") {
            error.add(name + "不能为空");
            return false;
        }
        return true;
    }
    private boolean check(Integer str,String name){
        if(str==null) {
            error.add(name + "不能为空");
            return false;
        }
        return true;
    }
    private boolean check(Date str,String name){
        if(str==null) {
            error.add(name + "不能为空");
            return false;
        }
        return true;
    }
    public String save(){
        //清空错误信息
        error.clear();
        Date date=new Date();
        Timestamp now = new Timestamp(date.getTime());
<<<<<<< HEAD
=======
        check(task.getArea(),"任务地区");
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
        check(task.getType_id(),"任务类别");
        check(task.getTitle(),"任务名称");
        check(task.getContent(),"任务内容");
        check(task.getImg(),"任务图片");
        check(task.getStart_time(),"任务开始时间");
        check(task.getEnd_time(),"任务结束时间");

<<<<<<< HEAD
        if(task.getStart_time().after(task.getEnd_time())){
            error.add("任务开始时间不能晚于结束时间");
        }
=======
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
        System.out.println(now);
        task.setCreate_time(now);
        task.setCreate_user(loginUser.getId());

        //表单验证失败
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = CommonResult.success(task);
        result = taskService.save(task);
        return  SUCCESS;
    }

    public String saveTaskOfUser(){
        error.clear();
        check(task.getId(),"任务id");

        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = taskService.saveTaskOfUser(loginUser.getId(),task.getId());
        return SUCCESS;
    }
    public String deleteUser_Task(){
        error.clear();
        check(task.getId(),"任务id");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = taskService.deleteUser_Task(loginUser.getId(),task.getId());
        return  SUCCESS;

    }
    public String getTaskByUserId(){
        result=taskService.getTaskByUserId(currentPage,pageSize,loginUser.getId());
        return SUCCESS;
    }
    public String deleteByTaskId(){
        error.clear();
        if(check(task.getId(),"任务id")==false){
            result = CommonResult.validateFail(error);
        }
        result = taskService.deleteByTaskId(task.getId(),loginUser.getId());
        return SUCCESS;
    }

    public String update(){
        //清空错误信息
        error.clear();
        check(task.getId(),"任务编号");
<<<<<<< HEAD
=======
        check(task.getArea(),"任务地区");
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
        check(task.getType_id(),"任务类别");
        check(task.getTitle(),"任务名称");
        check(task.getContent(),"任务内容");
        check(task.getImg(),"任务图片");
        check(task.getCreate_user(),"任务发起人");
        check(task.getStart_time(),"任务开始时间");
        check(task.getEnd_time(),"任务结束时间");
        check(task.getNumber(),"任务人数");

<<<<<<< HEAD
        if(task.getStart_time().after(task.getEnd_time())){
            error.add("任务开始时间不能晚于结束时间");
        }
=======
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = taskService.updateByTask(task);
        return  SUCCESS;
    }

    /**
     * 获取有效任务 即 在开始时间与结束时间内的任务
     * @return
     */
    public String getAllByTypeId() {
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(task.getType_id()==null)
<<<<<<< HEAD
            error.add("任务种类信息不能为空");
=======
            error.add("用户信息不能为空");
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result =  taskService.getTask(currentPage ,pageSize,task.getType_id());
        return SUCCESS;
    }


    public String getOneByTaskId(){
        error.clear();

        if(task.getId() == null){
            error.add("任务id不能为空");
            result = CommonResult.validateFail(error);
        }
        result = taskService.getTaskByTaskId(task.getId());
        return SUCCESS;
    }

<<<<<<< HEAD
    public String findLike() {
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(task.getTitle()==null)
            error.add("查询信息不能为空");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result =  taskService.findLike(currentPage ,pageSize,task.getTitle());
        return SUCCESS;
    }
=======
>>>>>>> d66299016d4b7920e8c3fe35dbb78a662e02af0c
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CommonResult getResult() {
        return result;
    }

    public void setResult(CommonResult result) {
        this.result = result;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public CommonResult getAuthResult() {
        return authResult;
    }

    public void setAuthResult(CommonResult authResult) {
        this.authResult = authResult;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
