package com.ssh.shop.dao;

import com.ssh.common.bean.PageBean;
import com.ssh.shop.domain.Shop;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */
public interface ShopDAO {


    Integer save(Shop shop);

    /**
     * 添加用户兑换商品记录
     * @return
     */
    Integer saveUser_Shop(Integer shopId,Integer userId);

    Integer deleteByShopId(Integer userId,Integer shopId);

    void update(Shop shop);

    /**
     * 分页查询
     * @param pageBean 分页信息
     * @return
     */
    PageBean<Shop> getAll(PageBean<Shop> pageBean);

    Shop getShopByShopId(Integer userId);

    /**
     * 获取用户兑换的商品信息
     * @return
     */
    PageBean<Shop> getShopByUserId(PageBean<Shop> pageBean, Integer  userId);


    /**
     * 获取记录总数
     * @return
     */
    Integer getShopCount();

    /**
     * 获取用户兑换总数
     * @return
     */
    Integer getShopOfUserCount(Integer userId);
}
