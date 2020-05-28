package com.ssh.common.bean;

import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
public class BaseDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
