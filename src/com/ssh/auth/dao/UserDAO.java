package com.ssh.auth.dao;

import com.ssh.auth.domain.User;
import com.ssh.common.bean.PageBean;
import com.ssh.shop.domain.Shop;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
public interface UserDAO {

     Integer save(User user);

     Integer deleteByUserId(Integer userId);

     void update(User user);

     /**
      * 分页查询
      * @param pageBean 分页信息
      * @return
      */
     PageBean<User> getAll(PageBean<User> pageBean);

     User getOneByUserId(Integer userId);


     User getUserByAccountNameAndPassword(User user);

     /**
      * 获取记录总数
      * @return
      */
     Integer getCount();


}
