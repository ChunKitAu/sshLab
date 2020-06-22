package com.ssh.task.dao.impl;

import com.ssh.auth.dao.UserDAO;
import com.ssh.auth.dao.impl.UserDAOImpl;
import com.ssh.auth.domain.User;
import com.ssh.common.bean.BaseDAO;
import com.ssh.common.bean.PageBean;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ssh.task.dao.*;
import com.ssh.task.domain.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.Id;

@Component("taskDAO")
public class TaskDAOImpl extends BaseDAO implements TaskDAO{

    private UserDAO userDAO;

    @Resource(name="userDAO")
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }
    //查询任务
    @Override
    public PageBean<Task> getAll(Integer userId,PageBean<Task> pageBean , Integer typeId) {
        String hql = "from Task where start_time < ? and end_time > ? and type_id = ?  order by end_time  ";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,new Date());
        query.setParameter(1,new Date());
        query.setParameter(2,typeId);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        tasks = (List<Task>)query.list();
        s.close();
        //保存获取的分页记录
        List<Task> collect = tasks.stream()
                .filter(e -> e.getStatus() == false)
                .collect(Collectors.toList());
        collect=change(collect);
        pageBean.setData(collect);
        return Deduplication(pageBean, userId);
    }

    @Override
    public PageBean<Task> getTaskByUserId(PageBean<Task> pageBean, Integer  userId){
        String sql="SELECT t.* " +
                "FROM task AS t " +
                "RIGHT JOIN task_user AS tu " +
                "ON t.`id` = tu.taskId " +
                "WHERE tu.`userId`= ? " +
                "ORDER BY id DESC";
        List<Integer> id;
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql).addEntity(Task.class);
        query.setParameter(0,userId);
        List<Task> tasks = (List<Task>)query.list();
        s.close();
        tasks=change(tasks);
        pageBean.setData(tasks);
        return pageBean;

    }

    //添加任务，返回任务编号
    @Override
    public Integer save(Task task){
        Session s = getSessionFactory().openSession();
        Integer id = (Integer) s.save(task);
        s.close();
        return id;

    }

    @Override
    public Integer saveUser_Task(Integer userId,Integer taskId){
        String sql = "Insert into task_user(taskId,userId) value (?,?)";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, taskId);
        query.setParameter(1, userId);
        int i = query.executeUpdate();
        s.close();
        return i;
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
        s.close();
        return true;
    }

    @Override
    public Integer deleteUser_Task(Integer userId,Integer taskId){
        String sql = "delete from task_user where taskId=? and userId=?";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, taskId);
        query.setParameter(1, userId);
        s.close();
        return query.executeUpdate();
    }

    //修改任务
    @Override
    public void updateByTask(Task task) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println(task);
        s.update(task);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Integer getCount(Integer typeId) {
        String hql = "from Task where start_time < ? and end_time > ? and type_id=?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,new Date());
        query.setParameter(1,new Date());
        query.setParameter(2,typeId);
        tasks = (List<Task>)query.list();
        s.close();
        return tasks.size();
    }

    @Override
    public Boolean successByTaskId(Integer taskId){
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        selectImpl<Integer> sqlSelect=(String str, Integer Id)->{
            SQLQuery query = s.createSQLQuery(str);
            query.setParameter(0, Id);
            return query.list();
        };
        if(sqlSelect.select("select id from task where id=? and status=false",taskId).size()==0)
            return false;
        Task task=getOneByTaskId(taskId);
        task.setStatus(true);

        List<Integer> userIds = sqlSelect.select("select userId from task_user where task_user.taskId=?", taskId);

        SQLQuery sql2 = s.createSQLQuery("UPDATE `user` SET integral = ? WHERE id = ? ");
        for(Iterator<Integer> it=userIds.iterator();it.hasNext();){
            User user=userDAO.getOneByUserId(it.next());
            sql2.setParameter(0,user.getIntegral()+task.getIntegral());
            sql2.setParameter(1,user.getId());
            int x = sql2.executeUpdate();
        }
        SQLQuery sql = s.createSQLQuery("UPDATE task SET  `status` = 1 WHERE id = ? ");
        sql.setParameter(0,task.getId());
        int i = sql.executeUpdate();

        s.getTransaction().commit();
        s.close();
        return true;
    }

    @Override
    public Integer getCountByUserId(Integer userId){
         String sql="SELECT t.* " +
                "FROM task AS t " +
                "RIGHT JOIN task_user AS tu " +
                "ON t.`id` = tu.taskId " +
                "WHERE tu.`userId`= ? ";
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
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, userId);
        query.setParameter(1, taskId);
        List<Task> tasks = (List<Task>)query.list();
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
        tasks=change(tasks);
        s.close();
        //查询id只会返回一条数据
        if (tasks.size() != 0){
            return tasks.get(0);
        }else return null;
    }

    //模糊查询
    @Override
    public PageBean<Task> findlike(Integer userId,PageBean<Task> pageBean , String str) {
        String hql = "from Task where title like '%"+str+"%'";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        tasks = (List<Task>)query.list();
        tasks=change(tasks);
        s.close();
        //保存获取的分页记录
        pageBean.setData(tasks);
        return Deduplication(pageBean, userId);
    }
    @Override
    public Integer getCountLike(String str){
        String sql = "select * from task where title like ?";
        List<Task> tasks;
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        String Temp="%"+str+"%";
        query.setParameter(0, Temp);
        tasks = (List<Task>)query.list();
        s.close();
        return tasks.size();
    }

    /**
     * 根据用户id，筛选出所有用户接受的任务，并将人数取反
     * @param pageBean
     * @param userId
     * @return
     */
    @Override
    public PageBean<Task> Deduplication(PageBean<Task> pageBean ,Integer userId){
        for(Task task:pageBean.getData()){
            if(find(userId,task.getId())>0){
                task.setNumber(~task.getNumber());
            }
        }
        return pageBean;
    }

    /**
     * 获取用户所创建的任务
     */
    @Override
    public PageBean<Task> getTaskByAuthor(PageBean<Task> pageBean,Integer userId){
        String hql = "from Task where create_user = ? order by id desc";
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,userId);
        List<Task> tasks = (List<Task>)query.list();
        tasks=change(tasks);
        pageBean.setData(tasks);
        pageBean.setTotal(tasks.size());
        s.close();
        return pageBean;
    }

    public List<Task> change(List<Task> tasks){
        for(Task task:tasks){
            task.setType_id(task.getType_id()-1);
        }
        return tasks;
    }
}