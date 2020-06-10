package com.ssh.shop.service.impl;

import com.ssh.auth.dao.UserDAO;
import com.ssh.auth.domain.User;
import com.ssh.common.bean.CommonResult;
import com.ssh.common.bean.PageBean;
import com.ssh.shop.dao.ShopDAO;
import com.ssh.shop.domain.Shop;
import com.ssh.shop.service.ShopService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auther ChunKitAu
 * @create 2020-05-18 18
 */

@Component("shopService")
public class ShopServiceImpl implements ShopService {

    private ShopDAO shopDAO;

    private UserDAO userDAO;



    @Resource(name = "shopDAO")
    public void setShopDAO(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public ShopDAO getShopDAO() {
        return shopDAO;
    }


    @Resource(name = "userDAO")
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public CommonResult save(Shop shop) {
        Integer save = shopDAO.save(shop);
        if(save > 0)
            return CommonResult.success();
        else
            return CommonResult.fail();
    }

    @Override
    public CommonResult deleteByShopId(Integer shopId) {
        Integer integer = shopDAO.deleteByShopId(shopId);
        if(integer > 0){
            return CommonResult.success();
        }else return CommonResult.fail();
    }

    @Override
    public CommonResult update(Shop shop) {
        shopDAO.update(shop);
        return CommonResult.success();
    }

    @Override
    public CommonResult getAll(Integer currentPage,Integer pageSize) {
        PageBean<Shop> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        //设置记录总数
        pageBean.setTotal(shopDAO.getShopCount());
        //获取总页数
         pageBean.setTotalPage( pageBean.getTotal() % pageSize == 0 ? pageBean.getTotal() / pageSize : pageBean.getTotal() / pageSize +1);

        if(currentPage < 1) {
            //当前页数小于1
            CommonResult result =  CommonResult.fail();
            result.setMessage("当前页数小于总页数");
            return result;
        } else if(currentPage > pageBean.getTotalPage()){
            //页数超过总页数
            CommonResult result =  CommonResult.fail();
            result.setMessage("当前页数大于总页数");
            return result;
        } else pageBean.setCurrPage(currentPage);//没超

        pageBean = shopDAO.getAll(pageBean);

        return CommonResult.success(pageBean);
    }

    @Override
    public CommonResult getShopByShopId(Integer userId) {
        Shop shop =  shopDAO.getShopByShopId(userId);
        if(shop != null){
            return CommonResult.success(shop);
        } else {
            //重写返回信息
            CommonResult result = CommonResult.fail();
            result.setMessage("当前礼品不存在");
            return result;
        }
    }

    @Override
    public CommonResult getShopByUserId(Integer currentPage, Integer pageSize, Integer userId) {
        PageBean<Shop> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        //设置用户兑换记录总数
        pageBean.setTotal(shopDAO.getShopOfUserCount(userId));
        //获取总页数
        pageBean.setTotalPage( pageBean.getTotal() % pageSize == 0 ? pageBean.getTotal() / pageSize : pageBean.getTotal() / pageSize +1);

        if(currentPage < 1) {
            //当前页数小于1
            CommonResult result =  CommonResult.fail();
            result.setMessage("当前页数小于总页数");
            return result;
        } else if(currentPage > pageBean.getTotalPage()){
            //页数超过总页数
            CommonResult result =  CommonResult.fail();
            result.setMessage("当前页数大于总页数");
            return result;
        } else pageBean.setCurrPage(currentPage);//没超

        pageBean = shopDAO.getShopByUserId(pageBean,userId);

        return CommonResult.success(pageBean);


    }

    @Override
    public CommonResult saveShopOfUser(Integer userId,Integer shopId) {
        Shop shop = shopDAO.getShopByShopId(shopId);
        User user = userDAO.getOneByUserId(userId);
        if( user.getIntegral() >= shop.getIntegral()){
            if(shopDAO.saveUser_Shop(shopId,userId) > 0)
                return CommonResult.success();
            else
                return CommonResult.fail();
        }else{
            CommonResult result = CommonResult.fail();
            result.setMessage("用户积分不够!");
            return result;
        }

    }


}
