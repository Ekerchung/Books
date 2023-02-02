package com.shung.dao.Imp;

import com.shung.bean.Book;
import com.shung.bean.Order;
import com.shung.dao.BaseDao;
import com.shung.dao.OrderDao;
import com.shung.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @Description: OrderDao的實現類
 * @author: Eker
 * @date: 2023/1/9 下午 04:44
 * @version: V1.0
 */

public class OrderDaoImp extends BaseDao implements OrderDao {
    /**
     * @titile: saveOrder
     * @description: 保存訂單
     * @param order 訂單對象
     * @return: int 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2023/2/2 下午 04:32
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(orderId,createTime,price,`status`,userId) values (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

    /**
     * @titile: queryOrders
     * @description: 查詢全部訂單
     * @return: List<Order> 返回全部訂單資訊List
     * @author: Eker
     * @date: 2023/2/2 下午 04:34
     */
    @Override
    public List<Order> queryOrders() {
        String sql = "select * from t_order order by createTime desc";
        List<Order> orders = queryMulti(sql, Order.class);
        return orders;
    }

    /**
     * @titile: changeOrderStatus
     * @description: 修改訂單狀態
     * @param orderId 訂單id
     * @param status 修改之訂單狀態。0 未出貨；1 已出貨；2 已收貨
     * @return: int 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2023/2/2 下午 04:34
     */
    @Override
    public int changeOrderStatus(String orderId,Integer status) {
        String sql = "UPDATE t_order SET `status` = ? WHERE orderId = ?";
        return update(sql,status,orderId);
    }


    /**
     * @titile: queryOrderByUserId
     * @description: 查詢使用者歷史訂單
     * @param userId 使用者用戶id
     * @return: List<Order> 返回使用者歷史訂單List
     * @author: Eker
     * @date: 2023/2/2 下午 04:36
     */
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select * from t_order where userId=?";
        List<Order> orders = queryMulti(sql, Order.class,userId);
        return orders;
    }

    /**
     * @titile: queryForMyOrderTotalCount
     * @description: 查詢使用者總訂單數量
     * @param userId 使用者用戶id
     * @return: Integer 返回使用者總訂單數量
     * @author: Eker
     * @date: 2023/2/2 下午 04:37
     */
    @Override
    public Integer queryForMyOrderTotalCount(Integer userId) {
        String sql = "select count(*) from t_order where userId = ?";
        Number pageTotalCount = (Number) queryScala(sql,userId);
        return pageTotalCount.intValue();
    }

    /**
     * @titile: queryTotalCount
     * @description: 查詢全部訂單數量
     * @return: Integer 返回全部訂單數量
     * @author: Eker
     * @date: 2023/2/2 下午 04:38
     */
    @Override
    public Integer queryTotalCount() {
        String sql = "select count(*) from t_order";
        Number pageTotalCount = (Number) queryScala(sql);
        return pageTotalCount.intValue();
    }

    /**
     * @titile: queryForPageItemsByUserId
     * @description: 查詢該分頁之使用者訂單資訊
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @param userId 使用者用戶id
     * @return: List<Order> 返回該分頁之使用者訂單資訊
     * @author: Eker
     * @date: 2023/2/2 下午 04:39
     */
    @Override
    public List<Order> queryForPageItemsByUserId(int pageNo, int pageSize, Integer userId) {
        String sql = "select * from t_order where userId=? order by createTime desc limit ?,?";
        int beginNo = (pageNo - 1) * pageSize;
        //調用BaseDao下的queryMulti方法查詢訂單資訊
        List<Order> items = queryMulti(sql, Order.class, userId, beginNo, pageSize);
        return items;
    }

    /**
     * @titile: queryForPageItems
     * @description: 查詢該分頁訂單資訊
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @return: List<Order> 返回該分頁訂單資訊
     * @author: Eker
     * @date: 2023/2/2 下午 04:40
     */
    @Override
    public List<Order> queryForPageItems(int pageNo, int pageSize) {
        String sql = "select * from t_order order by createTime desc limit ?,?";
        int beginNo = (pageNo - 1) * pageSize;
        //調用BaseDao下的queryMulti方法查詢訂單資訊
        List<Order> items = queryMulti(sql, Order.class, beginNo, pageSize);
        return items;
    }
}
