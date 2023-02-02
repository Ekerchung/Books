package com.shung.service.imp;

import com.shung.bean.*;
import com.shung.dao.BookDao;
import com.shung.dao.Imp.BookDaoImp;
import com.shung.dao.Imp.OrderDaoImp;
import com.shung.dao.Imp.OrderItemDaoImp;
import com.shung.dao.OrderDao;
import com.shung.dao.OrderItemDao;
import com.shung.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: OrderService的實現類
 * @author: Eker
 * @date: 2023/1/10 上午 10:26
 * @version: V1.0
 */
public class OrderServiceImp implements OrderService {
    OrderDao orderDao = new OrderDaoImp();
    OrderItemDao orderItemDao = new OrderItemDaoImp();
    BookDao bookDao = new BookDaoImp();
    /**
     * @titile: createOrder
     * @description: 建立訂單
     * @param cart 購物車對象
     * @param userId 使用者用戶id
     * @return: String 返回訂單id
     * @author: Eker
     * @date: 2023/2/2 下午 06:57
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //1. 保存訂單對象order到數據庫
        //將cart對象轉為order對象
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //調用saveOrder方法保存訂單
        orderDao.saveOrder(order);

        //2. 保存訂單項對象orderItem到數據庫
        //遍歷購物車的購物項對象cartItem，並將購物項內容保存至訂單項orderItem對象中
        for(Map.Entry<Integer,CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            //將購物項內容轉化到訂單項orderItem對象
            OrderItem orderItem = new OrderItem(null,orderId,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), cartItem.getImgPath());
            //保存orderItem對象至數據庫
            orderItemDao.saveOrderItem(orderItem);
            //更新商品庫存信息
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setB_sales(book.getB_sales() + cartItem.getCount());
            book.setB_stoke(book.getB_stoke() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        //清空購物車
        cart.clear();
        //返回訂單id
        return orderId;

    }

    /**
     * @titile: showAllOrders
     * @description: 調用OrderDao查詢全部訂單
     * @param null
     * @return: List<Order> 返回全部訂單資訊
     * @author: Eker
     * @date: 2023/2/2 下午 06:58
     */
    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    /**
     * @titile: sendOrder
     * @description: 修改訂單狀態(出貨)
     * @param orderId 訂單id
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 06:59
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);

    }

    /**
     * @titile: showOrderDetail
     * @description: 調用OrderItemDao查詢訂單之訂單項
     * @param orderId 訂單id
     * @return: List<OrderItem> 返回訂單之訂單項資訊
     * @author: Eker
     * @date: 2023/2/2 下午 07:00
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(orderId);
        return orderItems;
    }

    /**
     * @titile: showMyOrder
     * @description: 調用OrderDao查詢使用者歷史訂單
     * @param userId 使用者用戶id
     * @return: List<Order> 返回使用者歷史訂單資訊
     * @author: Eker
     * @date: 2023/2/2 下午 07:01
     */
    @Override
    public List<Order> showMyOrder(Integer userId) {
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        return orders;
    }

    /**
     * @titile: receiveOrder
     * @description: 修改訂單狀態(收貨)
     * @param orderId 使用者用戶id
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 07:02
     */
    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);

    }

    /**
     * @titile: page
     * @description: Order類的分頁功能方法
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @param userId 使用者用戶id
     * @return: Page 返回分頁資訊
     * @author: Eker
     * @date: 2023/2/2 下午 07:02
     */
    @Override
    public Page page(int pageNo, int pageSize, Integer userId) {
        Page page = new Page();
        //1. 設置pageSize
        page.setPageSize(pageSize);

        //3. 設置pageTotalCount
        //3.1 調用bookDao裡面的queryForPageTotalCount方法取得pageTotalCount
        Integer pageTotalCount = orderDao.queryForMyOrderTotalCount(userId);
        page.setPageTotalCount(pageTotalCount);
        //4. 設置pageTotal
        //4.1 利用公式求得pageTotal
            Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal = pageTotal + 1;
        }


        //2. 設置pageNo
        //設置數據邊界的有效範圍
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        if(pageTotal == 0){
            pageTotal = 1;
            pageNo = 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        //5. 設置items
        //5.1 調用bookDao裡面的queryForPageItems方法取得items
        List<Order> items = orderDao.queryForPageItemsByUserId(pageNo,pageSize,userId);
        page.setItems(items);

        return page;
    }

    /**
     * @titile: pageForManager
     * @description: 訂單管理的分頁功能方法
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @return: Page 返回分頁資訊
     * @author: Eker
     * @date: 2023/2/2 下午 07:04
     */
    @Override
    public Page pageForManager(int pageNo, int pageSize) {
        Page page = new Page();
        //1. 設置pageSize
        page.setPageSize(pageSize);

        //3. 設置pageTotalCount
        //3.1 調用bookDao裡面的queryTotalCount方法取得pageTotalCount
        Integer pageTotalCount = orderDao.queryTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //4. 設置pageTotal
        //4.1 利用公式求得pageTotal
            Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal = pageTotal + 1;
        }


        //2. 設置pageNo
        //設置數據邊界的有效範圍
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        if(pageTotal == 0){
            pageTotal = 1;
            pageNo = 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        //5. 設置items
        //5.1 調用bookDao裡面的queryForPageItems方法取得items
        List<Order> items = orderDao.queryForPageItems(pageNo,pageSize);

        page.setItems(items);

        return page;
    }
}
