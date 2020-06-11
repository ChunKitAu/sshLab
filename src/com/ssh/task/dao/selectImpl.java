package com.ssh.task.dao;

import java.util.List;

/**
 * @author ZhangRunKai
 * @date 2020/6/11 16:31
 */
@FunctionalInterface
public interface selectImpl<E> {
    List<E> select(String sql, Integer i);
}
