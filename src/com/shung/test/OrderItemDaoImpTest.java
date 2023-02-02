package com.shung.test;

import com.shung.bean.OrderItem;
import com.shung.dao.Imp.OrderItemDaoImp;
import com.shung.dao.OrderItemDao;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2023/1/9 下午 05:34
 * @version: V1.0
 */
public class OrderItemDaoImpTest {
OrderItemDao orderItemDao = new OrderItemDaoImp();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"12345","歐埋尬",2,new BigDecimal(100),new BigDecimal(200),"11223"));
        orderItemDao.saveOrderItem(new OrderItem(null,"12345","歐埋尬2",1,new BigDecimal(500),new BigDecimal(500),"11223"));
        orderItemDao.saveOrderItem(new OrderItem(null,"12345","歐埋尬3",3,new BigDecimal(200),new BigDecimal(600),"11223"));
        orderItemDao.saveOrderItem(new OrderItem(null,"12346","歐埋尬",2,new BigDecimal(100),new BigDecimal(200),"11223"));
        orderItemDao.saveOrderItem(new OrderItem(null,"12347","歐埋尬2",1,new BigDecimal(500),new BigDecimal(500),"11223"));
        orderItemDao.saveOrderItem(new OrderItem(null,"12347","歐埋尬3",3,new BigDecimal(200),new BigDecimal(600),"11223"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        System.out.println(orderItemDao.queryOrderItemsByOrderId("12347"));
    }
}