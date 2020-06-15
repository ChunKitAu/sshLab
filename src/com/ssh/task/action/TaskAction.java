package com.ssh.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.auth.domain.User;
import com.ssh.task.domain.Task;
import com.ssh.task.service.TaskService;
import com.ssh.common.bean.CommonResult;
import com.ssh.task.dao.TaskDAO;
import org.springframework.context.annotation.Scope;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Scope("prototype")
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
        System.out.println(true);
        if(str==null) {
            System.out.println("不能为空");
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

    /**
     * 保存任务
     * @return
     */
    public String save(){
        //清空错误信息
        error.clear();
        Date date=new Date();
        Timestamp now = new Timestamp(date.getTime());
        check(task.getType_id(),"任务类别");
//        System.out.println("Integer赋值于String:"+task.getType_id());
        check(task.getTitle(),"任务名称");
        check(task.getContent(),"任务内容");
        check(task.getImg(),"任务图片");
        check(task.getStart_time(),"任务开始时间");
        check(task.getEnd_time(),"任务结束时间");
        check(task.getNumber(),"任务人数");
        check(task.getPhone(),"联系方式");
        check(task.getIntegral(),"任务积分");
        check(loginUser.getId(),"登陆信息");
        if(task.getStart_time().after(task.getEnd_time())){
            error.add("任务开始时间不能晚于结束时间");
        }

        task.setCreate_time(now);
        //前端传的id从0 开始
        task.setType_id(task.getType_id() + 1);
        task.setCreate_user(loginUser.getId());
        //表单验证失败
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        System.out.println(task);
        result = CommonResult.success(task);
        result = taskService.save(task);
        System.out.println("===============================");
        return  SUCCESS;
    }
    /**
     * 用户接收任务
     * @return
     */
    public String saveTaskOfUser(){
        error.clear();
        check(task.getId(),"任务id");

        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = taskService.saveTaskOfUser(loginUser.getId(),task.getId());
        //result = taskService.saveTaskOfUser(user.getId(),task.getId());
        return SUCCESS;
    }
    /**
     * 接任务者取消任务
     * @return
     */
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

    public String seccessByTask(){
        error.clear();
        if(check(task.getId(),"任务id")==false){
            result = CommonResult.validateFail(error);
        }
        if(taskService.checktaskOfUser(task.getId(),loginUser.getId())==false){
            error.add("任务仅创建者可操作");
        }
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result = taskService.successByTaskId(task.getId());
        return SUCCESS;
    }

    public String getTaskByAuthor(){
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(loginUser.getId()==null)
            error.add("请先登陆");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result =  taskService.getTaskByAuthor(currentPage ,pageSize,loginUser.getId());
        return SUCCESS;
    }

    public String update(){
        //清空错误信息
        error.clear();
        check(task.getId(),"任务编号");
        check(task.getType_id(),"任务类别");
        check(task.getTitle(),"任务名称");
        check(task.getContent(),"任务内容");
        check(task.getImg(),"任务图片");
        check(task.getStart_time(),"任务开始时间");
        check(task.getEnd_time(),"任务结束时间");
        check(task.getNumber(),"任务人数");
        check(task.getPhone(),"联系方式");
        check(task.getIntegral(),"任务积分");
        check(loginUser.getId(),"登陆信息");
        if(task.getStart_time().after(task.getEnd_time())){
            error.add("任务开始时间不能晚于结束时间");
        }
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }

        Date date=new Date();
        Timestamp now = new Timestamp(date.getTime());
        task.setCreate_time(now);
        task.setCreate_user(loginUser.getId());
        task.setStatus(false);
        result = taskService.updateByTask(task);
        return  SUCCESS;
    }

    /**
     * 获取某种类型的任务
     * 返回值中，需要对任务人数number做一个判断
     *  number < 0:表示该任务已经接收过，取反后为剩余人数
     *  number >=0: 表示没有被接收过，为剩余人数
     * @return
     */
    public String getAllByTypeId() {
        error.clear();
        if(currentPage == null)
            error.add("当前页不能为空");
        if(pageSize == null)
            error.add("每页显示大小不能为空");
        if(task.getType_id()==null)
            error.add("任务种类信息不能为空");
        if(loginUser.getId()==null)
            error.add("请先登陆");
        if(error.size()!=0){
            result = CommonResult.validateFail(error);
            return SUCCESS;
        }
        result =  taskService.getTask(loginUser.getId(),currentPage ,pageSize,task.getType_id());
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
        result =  taskService.findLike(loginUser.getId(),currentPage ,pageSize,task.getTitle());
        return SUCCESS;
    }
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
