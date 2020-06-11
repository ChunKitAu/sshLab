package com.ssh.shop.service;

import com.ssh.common.bean.CommonResult;
import com.ssh.shop.domain.Shop;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */
public interface ShopService {
    CommonResult save(Shop shop);

    CommonResult deleteByShopId(Integer userId,Integer shopId);

    CommonResult update(Shop shop);

    /**
     * 分页查询
     * @return
     */
    CommonResult getAll(Integer currentPage,Integer pageSize);

    CommonResult getShopByShopId(Integer userId);

    /**
     * 获取用户兑换的商品信息
     * @return
     */
    CommonResult getShopByUserId(Integer currentPage,Integer pageSize,Integer  userId);

    /**
     * 添加用户兑换商品记录
     * @return
     */
    CommonResult saveShopOfUser(Integer userId,Integer shopId);

}
