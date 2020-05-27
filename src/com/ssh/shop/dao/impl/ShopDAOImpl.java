package com.ssh.shop.dao.impl;

import com.ssh.auth.domain.User;
import com.ssh.common.bean.BaseDAO;
import com.ssh.common.bean.PageBean;
import com.ssh.shop.dao.ShopDAO;
import com.ssh.shop.domain.Shop;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */
@Component("shopDAO")
public class ShopDAOImpl extends BaseDAO implements ShopDAO {
    @Override
    public Integer save(Shop shop) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        Integer id = (Integer) s.save(shop);
        s.getTransaction().commit();
        return id;
    }

    @Override
    public Integer saveUser_Shop(Integer shopId, Integer userId) {
        String sql = "Insert into shop_user(shopId,userId) value (?,?)";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        query.setParameter(0, shopId);
        query.setParameter(1, userId);
        return query.executeUpdate();
    }

    @Override
    public Integer deleteByShopId(Integer shopId) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        Shop shop = (Shop) s.get(Shop.class, shopId);
        //删除失败
        if (shop == null)
            return 0;

        s.delete(shop);
        s.getTransaction().commit();

        return 1;
    }

    @Override
    public void update(Shop shop) {
        Session s = getSessionFactory().openSession();
        s.beginTransaction();
        s.update(shop);
        s.getTransaction().commit();

    }

    @Override
    public PageBean<Shop> getAll(PageBean<Shop> pageBean) {
        String hql = "from Shop";
        List<Shop> shops;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        shops = (List<Shop>) query.list();
        //保存获取的分页记录
        pageBean.setData(shops);
        return pageBean;
    }

    @Override
    public Shop getShopByShopId(Integer userId) {
        String hql = "from Shop where id = ?";
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        query.setParameter(0, userId);
        List<Shop> shops = (List<Shop>) query.list();
        //查询id只会返回一条数据
        if (shops.size() != 0) {
            return shops.get(0);
        } else return null;
    }

    @Override
    public PageBean<Shop> getShopByUserId(PageBean<Shop> pageBean, Integer userId) {
        String sql = "SELECT s.* " +
                "FROM shop AS s " +
                "RIGHT JOIN shop_user AS su " +
                "ON s.`id` = su.`shopId`  " +
                "WHERE su.`userId`= ? ";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        //从第几条记录开始   页数从0开始  减1
        query.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize());
        query.setMaxResults(pageBean.getPageSize());
        query.setParameter(0, userId);
        List<Shop> shops = (List<Shop>) query.list();
        pageBean.setData(shops);

        return pageBean;


    }



    public Integer getShopOfUserCount(Integer userId){
        String sql = "SELECT s.id,s.img,s.integral,s.description,s.name " +
                "FROM shop AS s " +
                "RIGHT JOIN shop_user AS su " +
                "ON s.`id` = su.`shopId`  " +
                "WHERE su.`userId`= ? ";
        Session s = getSessionFactory().openSession();
        SQLQuery query = s.createSQLQuery(sql);
        //从第几条记录开始   页数从0开始  减1
        query.setParameter(0, userId);
        List<Shop> shops = (List<Shop>) query.list();

        return shops.size();

    }

    @Override
    public Integer getShopCount() {
        String hql = "from Shop";
        List<User> users;
        Session s = getSessionFactory().openSession();
        Query query = s.createQuery(hql);
        users = (List<User>) query.list();
        s.close();
        return users.size();
    }
}




