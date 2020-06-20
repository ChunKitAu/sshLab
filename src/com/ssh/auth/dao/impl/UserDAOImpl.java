package com.ssh.auth.dao.impl;

import com.ssh.auth.dao.UserDAO;
import com.ssh.auth.domain.User;
import com.ssh.common.bean.BaseDAO;
import com.ssh.common.bean.PageBean;
import com.ssh.shop.domain.Shop;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
@Component("userDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public Integer save(User user) {
        Session s = getSessionFactory().openSession();
        Integer id = (Integer) s.save(user);
        s.close();
        return id;
    }



    @Override
    public Integer deleteByUserId(Integer userId) {
        Session s = getSessionFactory().openSession();
        User user = (User)s.get(User.class,userId);
        //删除失败
        if(user == null)
            return 0;

        s.delete(user);
        s.close();
        return 1;
    }

    @Override
    public void update(User user) {
        Session s = getSessionFactory().openSession();
        s.update(user);
        s.close();
    }

    @Override
    public PageBean<User> getAll(PageBean<User> pageBean) {
        String hql = "from User";
        List<User> users;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        users = (List<User>)query.list();
        s.close();
        //保存获取的分页记录
        pageBean.setData(users);
        return pageBean;
    }

    @Override
    public User getOneByUserId(Integer userId) {
        String hql = "from User where id = ?";
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,userId);
        List<User> users = (List<User>) query.list();
        s.close();
        //查询id只会返回一条数据
        if (users.size() != 0){
            return users.get(0);
        }else return null;
    }

    @Override
    public User getUserByAccountNameAndPassword(User user) {
        String hql = "from User where accountName = ? And password = ?";
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0,user.getAccountName());
        query.setParameter(1,user.getPassword());
        List<User> users = (List<User>) query.list();
        s.close();
        //查询id只会返回一条数据
        if (users.size() != 0){
            return users.get(0);
        }else return null;
    }

    @Override
    public Integer getCount() {
        String hql = "from User";
        List<User> users;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        users = (List<User>)query.list();
        s.close();
        return users.size();
    }
}
