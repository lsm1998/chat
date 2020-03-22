/*
 * 作者：刘时明
 * 时间：2020/3/21-10:14
 * 作用：
 */
package com.lsm1998.chat.bean;

@FunctionalInterface
public interface ResultTask<E>
{
    E taskWork(Object args);
}
