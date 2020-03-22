/*
 * 作者：刘时明
 * 时间：2020/3/20-22:37
 * 作用：
 */
package com.lsm1998.chat.ui;

public interface BasePage
{
    void showPage();

    void closePage();

    default void switchPage(BasePage current, BasePage target)
    {
        current.closePage();
        target.showPage();
    }
}
