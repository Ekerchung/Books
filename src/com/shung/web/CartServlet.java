package com.shung.web;

import com.google.gson.Gson;
import com.shung.bean.Book;
import com.shung.bean.Cart;
import com.shung.bean.CartItem;
import com.shung.bean.User;
import com.shung.dao.BookDao;
import com.shung.dao.Imp.BookDaoImp;
import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 購物車的Servlet程序
 * @author: Eker
 * @date: 2022/12/15 下午 02:21
 * @version: V1.0
 */
public class CartServlet extends BaseServlet {

//    protected void loginCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         User user = (User) req.getSession().getAttribute("User");
//
//        if(user == null){
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
//        }else{
//            req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req,resp);
//        }
//    }
    BookService bookService = new BookServiceImp();

    /**
     * @titile: addItem
     * @description: 添加購物車商品項至購物車中
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:49
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取請求參數：商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //由商品編號查詢圖書訊息
        Book book = bookService.queryBookById(id);
        //將圖書訊息封裝到CartItem中
        CartItem cartItem = new CartItem(book.getB_id(),book.getB_name(),book.getB_price(),book.getB_price(),1,book.getB_imgPath());
        //調用cart.addItem方法添加商品項
        //先取得session域中的cart對象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //若session域中沒有購物車對象，則創建新的cart對象，並將此對象保存至session域中
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //重定向回原購物車商品地址
        String referer = req.getHeader("Referer");
        if(!referer.contains("pageNo")){
            resp.sendRedirect(req.getContextPath() + "/client/bookServlet?action=page");
            return;
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * @titile: ajaxAddItem
     * @description: ajax的添加商品項至購物車方法(取代addItem方法)
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:50
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取請求參數：商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //由商品編號查詢圖書訊息
        Book book = bookService.queryBookById(id);
        //將圖書訊息封裝到CartItem中
        CartItem cartItem = new CartItem(book.getB_id(),book.getB_name(),book.getB_price(),book.getB_price(),1,book.getB_imgPath());
        //調用cart.addItem方法添加商品項
        //先取得session域中的cart對象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //若session域中沒有購物車對象，則創建新的cart對象，並將此對象保存至session域中
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //將cartItem數量返回保存至Map
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        //Map內容返回至客戶端
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }

    /**
     * @titile: deleteItem
     * @description: 刪除購物車中之購物車商品項
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:51
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取請求參數：商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //先取得session域中的cart對象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //調用cart.addItem方法添加商品項
        cart.deleteItem(id);
        //重定向回原購物車商品地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @titile: clear
     * @description: 清空購物車
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:51
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先取得session域中的cart對象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //調用cart.addItem方法添加商品項
        cart.clear();
        //重定向回原購物車商品地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @titile: updateCount
     * @description: 修改購物車內購物車商品項之數量
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:07
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取請求參數：商品編號
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //獲取請求參數：商品數量
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        //先取得session域中的cart對象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //若session域中有購物車對象，則修改商品數量
        if(cart != null){
            //使用cart.updateCount方法修改商品數量
            cart.updateCount(id,count);
            //重定向回原購物車商品地址
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
