package com.shung.test;

import com.shung.bean.Cart;
import com.shung.bean.CartItem;
import com.shung.service.OrderService;
import com.shung.service.imp.OrderServiceImp;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2023/1/10 上午 11:30
 * @version: V1.0
 */
public class OrderServiceImpTest {
    OrderService orderService = new OrderServiceImp();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"40歲開始子彈存股翻倍賺",new BigDecimal(200),new BigDecimal(200),1,"222"));
        cart.addItem(new CartItem(2,"邏輯學入門課",new BigDecimal(200),new BigDecimal(200),1,"222"));
        String order = orderService.createOrder(cart, 1);
        System.out.println(order);
    }

    @Test
    public void showAllOrders() {
        System.out.println(orderService.showAllOrders());
    }

    @Test
    public void sendOrder() {
//        orderService.sendOrder("16733223507061");
    }

    @Test
    public void showOrderDetail() {
        System.out.println(orderService.showOrderDetail("16733223507061"));
    }

    @Test
    public void showMyOrder() {
        for (int i = 0; i < 10; i++) {

            System.out.println(orderService.showMyOrder(1));
        }
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("16733223507061");
    }
}