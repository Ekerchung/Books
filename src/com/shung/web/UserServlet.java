package com.shung.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.shung.bean.User;
import com.shung.service.UserService;
import com.shung.service.imp.UserServiceImp;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Description: 使用者註冊及登入之Servlet程序
 * @author: Eker
 * @date: 2022/12/15 下午 04:02
 * @version: V1.0
 */

public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImp();

    /**
     * @titile: login
     * @description: 確認用戶登入方法
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:01
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取驗證碼
        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //刪除session中的驗證碼，避免重複提交
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        //獲取輸入的驗證碼
        String inputCode = (String) req.getParameter("code");
        //獲取輸入的參數
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        //使用WebUtils將請求參數注入user中
        WebUtils.copyParameterToBean(req.getParameterMap(),user);
        //調用子類UserServiceImp中的userlogin()，確認登入帳號密碼是否正確
        User login = userService.userlogin(user);

        if(login == null || "".equals(login.getUsername())){
            req.setAttribute("msg","用戶名或密碼錯誤!!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }else if(token == null || !token.equals(inputCode)){
            req.setAttribute("msg","驗證碼錯誤!!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            req.getSession().setAttribute("user",login);
//            resp.sendRedirect(req.getHeader("Referer"));
            req.getRequestDispatcher("/client/bookServlet?action=page").forward(req,resp);

        }

    }

    /**
     * @titile: logout
     * @description: 用戶登出
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:01
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //銷毀session中的資料
        req.getSession().invalidate();
        //重定向回首頁
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * @titile: ajaxExistUsername
     * @description: 用ajax確認用戶名是否存在
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:02
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 獲取輸入的參數username
        String username = req.getParameter("username");
        //2. 調用userService.existUsername方法確認username是否存在
        boolean existUsername = userService.existUsername(username);
        //3. 將返回的結果封裝到Map對象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        //4. 將Map對象回傳給客戶端
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }

    /**
     * @titile: regist
     * @description: 確認用戶註冊的方法
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:02
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取輸入之用戶名
        String username = req.getParameter("username");
        //獲取輸入之用戶密碼
        String password = req.getParameter("password");
        //獲取輸入之用戶信箱
        String email = req.getParameter("email");
        //獲取輸入之用戶密碼確認
        String checknumber = req.getParameter("checknumber");
        //調用userService.existUsername確認用戶名是否存在
        boolean exist = userService.existUsername(username);
        if(exist){
            //若用戶名已存在，返回註冊頁
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }else{
            //若用戶名不存在，調用userService.userRegist保存用戶註冊資訊
            userService.userRegist(new User(null,username,password,email));
            //請求轉發至註冊成功頁面registSuccess.jsp
            req.getRequestDispatcher("/pages/user/registSuccess.jsp").forward(req,resp);

        }
    }
}
