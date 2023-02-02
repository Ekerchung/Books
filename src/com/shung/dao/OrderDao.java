package com.shung.dao;

import com.shung.bean.Order;

import java.util.List;

/**
 * @Description: OrderDao的接口
 * @author: Eker
 * @date: 2023/1/9 下午 04:34
 * @version: V1.0
 */
public interface OrderDao{
    //保存訂單
    public int saveOrder(Order order);
    //查詢訂單
    public List<Order> queryOrders();
    //修改訂單狀態
    public int changeOrderStatus(String orderId,Integer status);
    //查詢使用者歷史訂單
    public List<Order> queryOrderByUserId(Integer userId);
    //查詢使用者歷史訂單總數量
    public Integer queryForMyOrderTotalCount(Integer userId);
    //查詢該分頁之使用者訂單
    public List<Order> queryForPageItemsByUserId(int pageNo, int pageSize, Integer userId);
    //查詢總訂單數量
    public Integer queryTotalCount();
    //查詢該分頁訂單
    public List<Order> queryForPageItems(int pageNo, int pageSize);
}
