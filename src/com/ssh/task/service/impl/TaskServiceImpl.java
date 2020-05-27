package com.ssh.task.service.impl;

import com.ssh.task.domain.Task;
import com.ssh.task.service.TaskService;
import com.ssh.common.bean.CommonResult;
import com.ssh.common.bean.PageBean;
import org.springframework.stereotype.Component;
import com.ssh.task.dao.TaskDAO;

import javax.annotation.Resource;

@Component("taskService")
public class TaskServiceImpl  implements TaskService {

    private TaskDAO taskDAO;

    @Resource(name="taskDAO")
    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    @Override
    public CommonResult save(Task task){
        Integer save = taskDAO.save(task);
        if(save > 0)
            return CommonResult.success();
        else
            return CommonResult.fail();
    }

    @Override
    public CommonResult saveTaskOfUser(Integer userId,Integer taskId){
        if(taskDAO.find(userId,taskId)>0)
            return CommonResult.fail();
        if(taskDAO.saveUser_Task(userId,taskId) > 0)
            return CommonResult.success();
        else
            return CommonResult.fail();
    }

    @Override
    public CommonResult deleteUser_Task(Integer userId,Integer taskId){
        if(taskDAO.deleteUser_Task(userId,taskId)>0)
            return CommonResult.success();
        return CommonResult.fail();
    }
    @Override
    public CommonResult deleteByTaskId(Integer taskId,Integer userId){
        if(taskDAO.deleteByTaskId(taskId,userId)){
            return CommonResult.success();
        }
        return CommonResult.fail();
    }
    @Override
    public CommonResult getTask(Integer currentPage,Integer pageSize){
        PageBean<Task> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        //设置记录总数
        pageBean.setTotal(taskDAO.getCount());
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
        pageBean = taskDAO.getAll(pageBean);
        return CommonResult.success(pageBean);
    }

    @Override
    public CommonResult getTaskByUserId(Integer currentPage, Integer pageSize, Integer  userId){
        PageBean<Task> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        //设置记录总数
        pageBean.setTotal(taskDAO.getCountByUserId(userId));
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
        pageBean = taskDAO.getTaskByUserId(pageBean,userId);
        return CommonResult.success(pageBean);
    }

    @Override
    public CommonResult updateByTask(Task task){
        taskDAO.updateByTask(task);
        return CommonResult.success();
    }
    @Override
    public CommonResult getTaskByTaskId(Integer Id) {
        Task task =  taskDAO.getOneByTaskId(Id);
        if(task != null){
            return CommonResult.success(task);
        }
        else {
            //重写返回信息
            CommonResult result = CommonResult.fail();
            result.setMessage("当前任务不存在");
            return result;
        }
    }
}
