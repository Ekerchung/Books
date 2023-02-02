package com.shung.test;

import com.shung.bean.User;
import com.shung.dao.Imp.UserDaoImp;
import org.junit.Test;

/**
 * @Description:
 * @author: Eker
 * @date: 2022/11/18 下午 05:27
 * @version: V1.0
 */
public class UserImpTest {

    @Test
    public void queryByUsernametest() {
        UserDaoImp userImp = new UserDaoImp();
        User user = userImp.queryByUsername("jack");
        System.out.println(user);
    }

    @Test
    public void queryByUsernameAndPassword() {
        UserDaoImp userImp = new UserDaoImp();
        User user = userImp.queryByUsernameAndPassword("jack","abc1");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        UserDaoImp userImp = new UserDaoImp();
        User user = new User(2,"david","aabbyy","david@gmail.com");
        int count = userImp.saveUser(user);

    }
}