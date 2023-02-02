package com.shung.filter;

import com.shung.bean.User;
import com.shung.service.imp.UserServiceImp;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: manager權限檢查的filter類
 * @author: Eker
 * @date: 2023/1/16 下午 03:08
 * @version: V1.0
 */
public class ManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //取得session域中的user對象數據
        User user = (User) httpServletRequest.getSession().getAttribute("user");

        //判斷user對象是否為管理員，若是、則繼續訪問後台資源
        if(1 == user.getId()){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
