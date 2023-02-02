package com.shung.web;

import com.shung.bean.*;
import com.shung.service.OrderService;
import com.shung.service.imp.OrderServiceImp;
import com.shung.utils.JDBCUtils;
import com.shung.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 訂單Order的servlet程序
 * @author: Eker
 * @date: 2023/1/9 下午 04:29
 * @version: V1.0
 */
public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImp();
    /**
     * @titile: createOrder
     * @description: 創建訂單
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:55
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取session中的user及cart參數
        User user = (User) req.getSession().getAttribute("user");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //若使用者未登入，則導到登入頁面
        if(user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        //獲取使用者id
        Integer userId = user.getId();
        //使用createOrder方法，創建order訂單，並返回訂單編號
        String orderId = orderService.createOrder(cart, userId);
        //保存訂單編號到session域中
        req.getSession().setAttribute("orderId",orderId);
        //使用重定向導至動單完成頁面，避免重複提交
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * @titile: showAllOrders
     * @description: 顯示全部訂單
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:56
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取全部訂單
        List<Order> orders = orderService.showAllOrders();
        //保存訂單到request域中
        req.setAttribute("orders",orders);
        //請求轉發至orderlist頁面顯示數據
        req.getRequestDispatcher("/pages/order/orderlist.jsp").forward(req,resp);
    }

    /**
     * @titile: sendOrder
     * @description: 修改訂單狀態(發貨)
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:56
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取訂單編號
        String orderId = req.getParameter("orderId");
        //使用sendOrder方法，更新訂單狀態
        orderService.sendOrder(orderId);
        //重定向回頁面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @titile: showOrderDetail
     * @description: 顯示訂單之訂單項內容
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:56
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取訂單編號
        String orderId = req.getParameter("orderId");
        //獲取訂單總金額
        String totalPrice = req.getParameter("totalPrice");
        //使用showOrderDetail方法獲取訂單項內容
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        //將訂單項內容保存至request域中
        req.setAttribute("orderItems",orderItems);
        //將訂單編號保存至request域中
        req.setAttribute("orderId",orderId);
        //將訂單總金額保存至request域中
        req.setAttribute("totalPrice",totalPrice);

        //請求轉發至orderDetail.jsp頁面顯示數據
        req.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(req,resp);

    }

    /**
     * @titile: showMyOrder
     * @description: 顯示使用者歷史訂單
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:56
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取session域中的user參數
        User user = (User) req.getSession().getAttribute("user");
        //若使用者未登入，則導到登入頁面
        if(user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = user.getId();
        //使用showMyOrder方法，取得使用者的訂單
        List<Order> orders = orderService.showMyOrder(userId);
        //將使用者的訂單orders保存至request域中
        req.setAttribute("orders",orders);
        //請求轉發至orderlist.jsp頁面顯示內容
        req.getRequestDispatcher( "/pages/order/orderlist.jsp").forward(req,resp);
    }

    /**
     * @titile: receiveOrder
     * @description: 修改訂單狀態(收貨)
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:57
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //獲取訂單編號
        String orderId = req.getParameter("orderId");
        //使用sendOrder方法，更新訂單狀態
        orderService.receiveOrder(orderId);
        //重定向回頁面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @titile: page
     * @description: 訂單的分頁功能
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 11:57
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        //將pageSize,pageNo傳入並調用bookService的page方法，得到完整的page參數設定(pageNo,pageSize,pageTotal,pageTotalCount,items)
        Page<Order> page = orderService.page(pageNo,pageSize,userId);

        //由於要將分頁抽取出來，依分頁條請求地址不同在各servlet中填入請求地址
        page.setUrl("orderServlet?action=page");
        //將page對象保存到request域中
        req.setAttribute("page",page);
        //將參數請求轉發至orderlist.jsp頁面
        req.getRequestDispatcher("/pages/order/myorderlist.jsp").forward(req,resp);


    }

    /**
     * @titile: pageForManager
     * @description: 管理者之訂單分頁功能
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/3 上午 12:07
     */
    protected void pageForManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        //將pageSize,pageNo傳入並調用bookService的page方法，得到完整的page參數設定(pageNo,pageSize,pageTotal,pageTotalCount,items)
        Page<Order> page = orderService.pageForManager(pageNo,pageSize);
        //由於要將分頁抽取出來，依分頁條請求地址不同在各servlet中填入請求地址
        page.setUrl("orderServlet?action=pageForManager");
        //將page對象保存到request域中
        req.setAttribute("page",page);
        //將參數請求轉發至orderlist.jsp頁面
        req.getRequestDispatcher("/pages/order/orderlist.jsp").forward(req,resp);


    }
}
