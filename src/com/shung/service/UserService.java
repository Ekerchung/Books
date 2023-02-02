package com.shung.service;

import com.shung.bean.User;

/**
 * @Description: 用戶登入及註冊之接口
 * @author: Eker
 * @date: 2022/11/21 下午 04:53
 * @version: V1.0
 */
public interface UserService {
    /**
     * @titile: userlogin
     * @description: 用戶登入
     * @param user 輸入用戶登入訊息
     * @return: 如果返回null，代表登入失敗，若有值，則登入成功
     * @author: Eker
     * @date: 2022/11/21 下午 04:57
     */
    public User userlogin(User user);

    /**
     * @titile: userRegist
     * @description: 用戶註冊
     * @param user 輸入用戶註冊訊息
     * @return:
     * @author: Eker
     * @date: 2022/11/21 下午 04:58
     */
    public void userRegist(User user);

    /**
     * @titile: existUsername
     * @description: 確認用戶名是否可用
     * @param user 輸入用戶訊息以供確認用戶名是否存在
     * @return: 若返回true，則用戶名已存在，若返回為false則反之
     * @author: Eker
     * @date: 2022/11/21 下午 05:01
     */
    public boolean existUsername(String username);

}
