package com.shung.web;

import com.shung.bean.Book;
import com.shung.bean.Page;
import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 前台的BookServlet\
 * @author: Eker
 * @date: 2023/1/6 下午 03:17
 * @version: V1.0
 */
public class ClientBookServlet extends BaseServlet{
    BookService bookService = new BookServiceImp();

    /**
     * @titile: page
     * @description: 使用者介面的分頁功能方法
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:52
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        //將pageSize,pageNo傳入並調用bookService的page方法，得到完整的page參數設定(pageNo,pageSize,pageTotal,pageTotalCount,items)
        Page<Book> page = bookService.page(pageNo,pageSize);
        //由於要將分頁抽取出來，依分頁條請求地址不同在各servlet中填入請求地址
        page.setUrl("client/bookServlet?action=page");
        //將page對象保存到request域中
        req.setAttribute("page",page);
        //將參數請求轉發至book_manager.jsp頁面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

    /**
     * @titile: pageByPrice
     * @description: 自定義商品價格區間之分頁方法
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:07
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //將pageSize,pageNo傳入並調用bookService的page方法，得到完整的page參數設定(pageNo,pageSize,pageTotal,pageTotalCount,items)
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        //須將min、max參數添加至分頁條地址中，否則點擊分頁條會跑到不帶價格查詢的分頁
        //使用StringBuilder將查詢分頁地址添加
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //若有查詢參數min，則加到分頁地址後面
        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //若有查詢參數max，則加到分頁地址後面
        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //由於要將分頁抽取出來，依分頁條請求地址不同在各servlet中填入請求地址
        page.setUrl(sb.toString());

        //將page對象保存到request域中
        req.setAttribute("page",page);
        //將參數請求轉發至book_manager.jsp頁面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
