package com.ssh.task.service;

import com.ssh.task.domain.Task;
import com.ssh.common.bean.*;

public interface TaskService {

    /**
     * 保存任务信息
     * @param task
     * @return
     */
    CommonResult save(Task task);

    /**
     * 根据种类查询任务
     * @param pageBean
     * @return
     */
    CommonResult getTask(Integer userId,Integer currentPage,Integer pageSize,Integer typeId);

    /**
     * 根据任务id，删除任务
     * @return
     */
    CommonResult deleteByTaskId(Integer taskId,Integer userId);

    /**
     * 修改任务
     * @param task
     * @return
     */
    CommonResult updateByTask(Task task);
    /**
     * 查询单个任务
     */
    CommonResult getTaskByTaskId(Integer Id);


    CommonResult saveTaskOfUser(Integer userId,Integer taskId);

    CommonResult deleteUser_Task(Integer userId,Integer taskId);

    CommonResult getTaskByUserId(Integer currentPage, Integer pageSize,Integer  userId);

    /**
     *模糊查询任务名为str的任务
     */
    CommonResult findLike(Integer userId,Integer currentPage, Integer pageSize, String str);
}
