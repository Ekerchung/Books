package com.shung.service.imp;

import com.shung.bean.User;
import com.shung.dao.Imp.UserDaoImp;
import com.shung.dao.UserDao;
import com.shung.service.UserService;

/**
 * @Description: 實現用戶登入及註冊
 * @author: Eker
 * @date: 2022/11/21 下午 05:04
 * @version: V1.0
 */
public class UserServiceImp implements UserService {
    private UserDao userDaoImp = new UserDaoImp();

    /**
     * @titile: userlogin
     * @description: 使用者登入功能
     * @param user 使用者輸入之登入資訊
     * @return: User 返回數據庫內使用者訊息
     * @author: Eker
     * @date: 2023/2/2 下午 07:05
     */
    @Override
    public User userlogin(User user) {

        return(userDaoImp.queryByUsernameAndPassword(user.getUsername(), user.getPassword()));

    }

    /**
     * @titile: userRegist
     * @description: 調用UserDaoImp保存用戶註冊資訊
     * @param user 用戶註冊資訊
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 07:09
     */
    @Override
    public void userRegist(User user) {
        userDaoImp.saveUser(user);
    }

    /**
     * @titile: existUsername
     * @description: 判斷用戶名是否存在
     * @param username 登入id
     * @return: boolean 若用戶名存在，返回true；若用戶名不存在，返回false
     * @author: Eker
     * @date: 2023/2/2 下午 07:10
     */
    @Override
    public boolean existUsername(String username) {
       if(userDaoImp.queryByUsername(username) == null){
           return false;
       }else {
           return true;
       }
    }
}
