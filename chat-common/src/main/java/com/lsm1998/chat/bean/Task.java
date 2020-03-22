/*
 * 作者：刘时明
 * 时间：2020/3/21-10:09
 * 作用：
 */
package com.lsm1998.chat.bean;

@FunctionalInterface
public interface Task
{
    void asyncWork(Object args);
}
