package com.shung.web;

import com.shung.bean.User;
import com.shung.service.UserService;
import com.shung.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 用戶註冊的Servlet程序(已過期，使用UserServlet取代)
 * @author: Eker
 * @date: 2022-11-17 上午 01:21
 * @version: V1.0
 */
public class RegistServlet extends HttpServlet{
    private UserService userServiceImp = new UserServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String checknumber = req.getParameter("checknumber");
        boolean exist = userServiceImp.existUsername(username);
        if(exist){
            System.out.println("用戶名稱已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }else{
            System.out.println("註冊成功");
            userServiceImp.userRegist(new User(null,username,password,email));
            req.getRequestDispatcher("/pages/user/registSuccess.jsp").forward(req,resp);

        }

    }


}
//