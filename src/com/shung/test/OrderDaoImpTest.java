package com.shung.test;

import com.shung.bean.Order;
import com.shung.dao.Imp.OrderDaoImp;
import com.shung.dao.OrderDao;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2023/1/9 下午 05:07
 * @version: V1.0
 */
public class OrderDaoImpTest {
OrderDao orderDao = new OrderDaoImp();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("12345",new Date(),new BigDecimal(100),0,1));
        orderDao.saveOrder(new Order("12348",new Date(),new BigDecimal(300),0,1));
        orderDao.saveOrder(new Order("12346",new Date(),new BigDecimal(500),0,2));
        orderDao.saveOrder(new Order("12347",new Date(),new BigDecimal(400),0,5));
    }

    @Test
    public void queryOrders() {
        System.out.println(orderDao.queryOrders());
    }

    @Test
    public void changeOrderStatus() {
        System.out.println(orderDao.changeOrderStatus("16738449198561", 0));
    }

    @Test
    public void queryOrderByUserId() {
        System.out.println(orderDao.queryOrderByUserId(1));
    }

    @Test
    public void queryForPageItemsByUserId() {
        for (int i = 0; i < 10; i++) {

            System.out.println(orderDao.queryForPageItemsByUserId(1, 4, 1));
        }
    }
}