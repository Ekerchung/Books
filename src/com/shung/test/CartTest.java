package com.shung.test;

import com.shung.bean.Cart;
import com.shung.bean.CartItem;
import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2023/1/7 下午 04:56
 * @version: V1.0
 */
public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(51,"test23",new BigDecimal(200),new BigDecimal(200),1,null));
        cart.addItem(new CartItem(52,"test24",new BigDecimal(400),new BigDecimal(400),1,null));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(51,"test23",new BigDecimal(200),new BigDecimal(200),1,null));
        cart.addItem(new CartItem(52,"test24",new BigDecimal(400),new BigDecimal(400),1,null));

        System.out.println(cart);

        cart.deleteItem(50);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(51,"test23",new BigDecimal(200),new BigDecimal(200),1,null));
        cart.addItem(new CartItem(52,"test24",new BigDecimal(400),new BigDecimal(400),1,null));

        System.out.println(cart);

        cart.deleteItem(50);

        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(51,"test23",new BigDecimal(200),new BigDecimal(200),1,null));
        cart.addItem(new CartItem(52,"test24",new BigDecimal(400),new BigDecimal(400),1,null));

        System.out.println(cart);

        cart.deleteItem(50);

        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(50,"test22",new BigDecimal(300),new BigDecimal(300),1,null));
        cart.addItem(new CartItem(51,"test23",new BigDecimal(200),new BigDecimal(200),1,null));
        cart.addItem(new CartItem(52,"test24",new BigDecimal(400),new BigDecimal(400),1,null));
        cart.updateCount(52,4);
        System.out.println(cart);
    }
}