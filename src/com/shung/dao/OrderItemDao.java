package com.shung.dao;

import com.shung.bean.OrderItem;

import java.util.List;

/**
 * @Description: OrderItemDao的接口
 * @author: Eker
 * @date: 2023/1/9 下午 05:21
 * @version: V1.0
 */
public interface OrderItemDao {
    //保存訂單之訂單項
    public int saveOrderItem(OrderItem orderItem);
    //查詢訂單之訂單項
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
