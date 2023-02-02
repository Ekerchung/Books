package com.shung.test;

import com.shung.bean.User;
import com.shung.service.UserService;
import com.shung.service.imp.UserServiceImp;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2022/11/21 下午 05:26
 * @version: V1.0
 */
public class UserServiceImpTest {
    UserService userService = new UserServiceImp();
    User user1 = new User(1, "admin", "admin", "andy@gmail.com");
    @Test
    public void userlogin() {
        System.out.println(userService.userlogin(user1));
    }

    @Test
    public void userRegist() {
        userService.userRegist(new User(4,"asda","dasoieqw","jfnd@gmail.com"));
    }

    @Test
    public void existUsernameTest() {
        System.out.println(userService.existUsername(user1.getUsername()));
    }
}