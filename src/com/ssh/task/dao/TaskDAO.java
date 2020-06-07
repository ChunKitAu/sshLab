package com.ssh.task.dao;

import com.ssh.task.domain.*;
import com.ssh.common.bean.PageBean;
public interface TaskDAO {

    //保存任务信息，并返回任务编号
    Integer save(Task task);
    //查询所有的任务
    PageBean<Task> getAll(Integer userId,PageBean<Task> pageBean,Integer typeId);
    //根据任务编号，删除id
    boolean deleteByTaskId(Integer id,Integer userId);
    //修改任务
    void updateByTask(Task task);
    //获取记录总数
    Integer getCount(Integer typeId);
    //查询单个任务
    Task getOneByTaskId(Integer Id);

    Integer saveUser_Task(Integer userId,Integer taskId);

    Integer find(Integer userId,Integer taskId);

    Integer deleteUser_Task(Integer userId,Integer taskId);

    PageBean<Task> getTaskByUserId(PageBean<Task> pageBean, Integer  userId);

    Integer getCountByUserId(Integer userId);

    PageBean<Task> findlike(Integer userId,PageBean<Task> pageBean , String str);

    Integer getCountLike(String str);

    //查询用户已接收的任务，并去重
    PageBean<Task> Deduplication(PageBean<Task> pageBean ,Integer userId);

}
