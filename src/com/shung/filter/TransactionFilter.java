package com.shung.filter;

import com.shung.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description: 事務的Filter
 * @author: Eker
 * @date: 2023/1/16 下午 05:15
 * @version: V1.0
 */
public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();//提交事務
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滾事務
            e.printStackTrace();
            throw new RuntimeException(e);//把異常拋給Tomcat伺服器，以顯示錯誤訊息頁面
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
