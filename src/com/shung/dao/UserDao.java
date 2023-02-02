package com.shung.dao;

import com.shung.bean.User;

/**
 * @Description: User接口
 * @author: Eker
 * @date: 2022/11/18 下午 04:37
 * @version: V1.0
 */
public interface UserDao {
//    1.根據帳號查詢用戶資料
    public User queryByUsername(String username);
//    2.根據帳號及密碼查詢用戶資料
    public User queryByUsernameAndPassword(String username,String password);
//    3.新增用戶資料
    public int saveUser(User user);
}
