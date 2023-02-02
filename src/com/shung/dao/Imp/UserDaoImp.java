package com.shung.dao.Imp;

import com.shung.bean.User;
import com.shung.dao.BaseDao;
import com.shung.dao.UserDao;
import com.shung.utils.JDBCUtils;

import java.sql.Connection;


/**
 * @Description: User實現類
 * @author: b8954
 * @date: 2022/11/18 下午 04:26
 * @version: V1.0
 */
public class UserDaoImp extends BaseDao implements UserDao {

    /**
     * @titile: queryByUsername
     * @description: 由用戶名稱查詢其相關資訊
     * @param username 用戶名稱
     * @return: User 用戶資料
     * @author: Eker
     * @date: 2022/11/21 下午 03:43
     */
    @Override
    public User queryByUsername(String username) {
        String sql = "select * from t_accounts where username = ?";
        return querySingle(sql, User.class, username);
    }

    /**
     * @titile: queryByUsernameAndPassword
     * @description: 由用戶名稱及密碼查詢其相關資訊
     * @param username 用戶名稱
     * @param password 用戶密碼
     * @return: 返回值為null，則帳號密碼有誤，若有值，則允許登入成功
     * @author: Eker
     * @date: 2022/11/21 下午 03:43
     */
    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_accounts where username = ? and password = ?";
        return querySingle(sql , User.class , username , password);
    }

    /**
     * @titile: saveUser
     * @description: 儲存用戶資料
     * @param user User類的用戶資料
     * @return: int 回傳影響資料行數，若為null 則為儲存失敗
     * @author: Eker
     * @date: 2022/11/21 下午 03:45
     */
    @Override
    public int saveUser(User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        String sql = "Insert into t_accounts(username,password,email) values(?,?,?)";
        return update(sql , username , password, email);
    }
}
