package com.ssh.task.dao.impl;

import com.ssh.common.bean.BaseDAO;
import com.ssh.common.bean.PageBean;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.ssh.task.dao.*;
import com.ssh.task.domain.*;
import org.springframework.stereotype.Component;

@Component("taskDAO")
public class TaskDAOImpl extends BaseDAO implements TaskDAO{

    //查询任务
    @Override
    public PageBean<Task> getAll(PageBean<Task> pageBean) {
        String hql = "from Task where start_time < ? and end_time > ?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,new Date());
        query.setParameter(1,new Date());
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        tasks = (List<Task>)query.list();
        s.close();
        //保存获取的分页记录
        pageBean.setData(tasks);
        return pageBean;
    }

    @Override
    public PageBean<Task> getTaskByUserId(PageBean<Task> pageBean, Integer  userId){
        //String sql = "select * from task as t1，task_user as t2 where t1.id = t2.taskId and t2.userId = ?";
        //select a.*, b.* from task a left outer join task_user b on a.id = b.taskId and b.userId=13
        String sql="select a.* from task a join task_user b on a.id = b.taskid and b.userId = ?";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        query.setParameter(0, userId);
        List<Task> shops = (List<Task>)query.list();
        pageBean.setData(shops);
        return pageBean;

    }

    //添加任务，返回任务编号
    @Override
    public Integer save(Task task){
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        Integer id = (Integer) s.save(task);
        s.getTransaction().commit();
        return id;
    }

    @Override
    public Integer saveUser_Task(Integer userId,Integer taskId){
        String sql = "Insert into task_user(taskId,userId) value (?,?)";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, taskId);
        query.setParameter(1, userId);
        return query.executeUpdate();
    }

    //删除任务，放回是否成功
    @Override
    public boolean deleteByTaskId(Integer id,Integer userId) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        Task task=(Task)s.get(Task.class,id);
        //删除失败
        if(task == null)
            return false;

        if(task.getCreate_user() != userId){
            return false;
        }

        s.delete(task);
        s.getTransaction().commit();

        return true;
    }

    @Override
    public Integer deleteUser_Task(Integer userId,Integer taskId){
        String sql = "delete from task_user where taskId=? and userId=?";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, taskId);
        query.setParameter(1, userId);
        return query.executeUpdate();
    }

    //修改任务
    @Override
    public void updateByTask(Task task) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        s.update(task);
        s.getTransaction().commit();
    }

    @Override
    public Integer getCount() {
        String hql = "from Task where create_time < ? and end_time > ?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,new Date());
        query.setParameter(1,new Date());
        tasks = (List<Task>)query.list();
        s.close();
        return tasks.size();
    }

    @Override
    public Integer getCountByUserId(Integer userId){
        String sql = "select a.*, b.* from task a join task_user b on a.id = b.taskid and b.userId = ?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, userId);
        tasks = (List<Task>)query.list();
        s.close();
        return tasks.size();
    }

    @Override
    public Integer find(Integer userId,Integer taskId){
        String sql = "select * from task_user where userId=? and taskId=?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, userId);
        query.setParameter(1, taskId);
        tasks = (List<Task>)query.list();
        System.out.println(tasks.size());
        s.close();
        return tasks.size();
    }

    @Override
    public  Task getOneByTaskId(Integer Id){
        String hql = "from Task where id = ?";
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,Id);
        List<Task> tasks = (List<Task>) query.list();
        //查询id只会返回一条数据
        if (tasks.size() != 0){
            return tasks.get(0);
        }else return null;
    }
}
