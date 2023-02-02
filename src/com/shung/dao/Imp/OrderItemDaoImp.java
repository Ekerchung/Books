package com.shung.dao.Imp;

import com.shung.bean.OrderItem;
import com.shung.dao.BaseDao;
import com.shung.dao.OrderItemDao;

import java.util.List;

/**
 * @Description: OrderItemDao的實現類
 * @author: Eker
 * @date: 2023/1/9 下午 05:26
 * @version: V1.0
 */
public class OrderItemDaoImp extends BaseDao implements OrderItemDao {
    /**
     * @titile: saveOrderItem
     * @description: 保存訂單項
     * @param orderItem 訂單項對象
     * @return: int 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2023/2/2 下午 06:27
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id`,orderId,`name`,`count`,price,totalPrice,imgPath) values(?,?,?,?,?,?,?)";
        return update(sql,orderItem.getId(),orderItem.getOrderId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getImgPath());
    }

    /**
     * @titile: queryOrderItemsByOrderId
     * @description: 查詢訂單之訂單項資訊
     * @param orderId 訂單id
     * @return: List<OrderItem> 返回訂單項資訊
     * @author: Eker
     * @date: 2023/2/2 下午 06:28
     */
    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select * from t_order_item where orderid = ?";
        List<OrderItem> orderItems = queryMulti(sql, OrderItem.class, orderId);
        return orderItems;
    }
}
