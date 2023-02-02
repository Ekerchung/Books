package com.shung.service;

import com.shung.bean.*;

import java.util.List;

/**
 * @Description: 訂單Order的service接口
 * @author: Eker
 * @date: 2023/1/9 下午 05:42
 * @version: V1.0
 */
public interface OrderService {

    //創建訂單
    public String createOrder(Cart cart, Integer userId);
    //顯示全部訂單資訊
    public List<Order> showAllOrders();
    //修改訂單狀態(發貨)
    public void sendOrder(String orderId);
    //顯示訂單之訂單項資訊
    public List<OrderItem> showOrderDetail(String orderId);
    //顯示使用者歷史訂單
    public List<Order> showMyOrder(Integer userId);
    //修改訂單狀態(收貨)
    public void receiveOrder(String orderId);
    //訂單的分頁方法
    public Page<Order> page(int pageNo, int pageSize,Integer userId);
    //管理者的訂單分頁方法
    public Page pageForManager(int pageNo, int pageSize);
}
