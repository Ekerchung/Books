package com.shung.web;

import com.shung.bean.Book;
import com.shung.bean.Page;
import com.shung.dao.BookDao;
import com.shung.dao.Imp.BookDaoImp;
import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 圖書管理的servlet程序
 * @author: Eker
 * @date: 2022/12/30 上午 12:25
 * @version: V1.0
 */

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * @titile: add
     * @description: 調用bookService.addBook添加圖書
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:40
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pageNo = req.getParameter("pageNo");
        pageNo += 1;
        Book book = new Book();
        //使用WebUtils.copyParameterToBean封裝圖書資訊
        WebUtils.copyParameterToBean(req.getParameterMap(),book);
        //調用bookService.addBook添加圖書
        bookService.addBook(book);
        //重定向回原網址(使用重定向避免重複提交)
        resp.sendRedirect("bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * @titile: update
     * @description: 調用bookService.updateBook修改圖書資訊
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:40
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = new Book();
        //使用WebUtils.copyParameterToBean封裝圖書資訊
        WebUtils.copyParameterToBean(req.getParameterMap(),book);
        //調用bookService.updateBook修改圖書資訊
        bookService.updateBook(book);
        //重定向回原網址(使用重定向避免重複提交)
        resp.sendRedirect("bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * @titile: delete
     * @description: 調用bookService.deleteBookById刪除圖書
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:44
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //獲取圖書id參數
        int id = Integer.parseInt(req.getParameter("b_id"));
        //調用bookService.deleteBookById刪除圖書
        bookService.deleteBookById(id);
        //重定向回原網址(使用重定向避免重複提交)
        resp.sendRedirect("bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * @titile: getBook
     * @description: 調用bookService.queryBookById獲取圖書資訊
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:45
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //獲取圖書id參數
        int id = WebUtils.parseInt(req.getParameter("b_id"),0);
        //調用bookService.queryBookById獲取圖書資訊
        Book book = bookService.queryBookById(id);
        //將圖書資訊保存至request域中
        req.setAttribute("book",book);
        //請求轉發至book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }


    /**
     * @titile: list
     * @description: 調用bookService.queryBooks查詢圖書資訊(多個)
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:46
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //調用bookService.queryBooks查詢圖書資訊(多個)
        List<Book> books = bookService.queryBooks();
        //將圖書資訊保存至request域中
        req.setAttribute("books",books);
        //請求轉發至book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * @titile: page
     * @description: 圖書的分頁方法
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:48
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        //將pageSize,pageNo傳入並調用bookService的page方法，得到完整的page參數設定(pageNo,pageSize,pageTotal,pageTotalCount,items)
        Page<Book> page = bookService.page(pageNo,pageSize);
        //由於要將分頁抽取出來，依分頁條請求地址不同在各servlet中填入請求地址
        page.setUrl("manager/bookServlet?action=page");
        //將page對象保存到request域中
        req.setAttribute("page",page);
        //將參數請求轉發至book_manager.jsp頁面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }
}
