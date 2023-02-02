package com.shung.web;

import com.shung.bean.User;
import com.shung.service.UserService;
import com.shung.service.imp.UserServiceImp;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 用戶登入的Servlet程序 (已過期，使用UserServlet取代)
 * @author: Eker
 * @date: 2022-11-17 上午 01:21
 * @version: V1.0
 */
public class LoginServlet extends HttpServlet{
    private UserService userServiceImp = new UserServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");

//        String username = req.getParameter("username");
//        String password = req.getParameter("password");

        User user = new User();
        //使用WebUtils將請求參數注入user中
        WebUtils.copyParameterToBean(req.getParameterMap(),user);
        User login = userServiceImp.userlogin(user);

        if(login == null){
            System.out.println("帳號密碼有誤，請重新輸入");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            System.out.println("登入成功");
            req.getRequestDispatcher("/pages/user/loginSuccess.jsp").forward(req,resp);
        }

    }
}
