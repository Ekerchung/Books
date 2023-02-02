package com.shung.bean;

import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 購物車的javabean容器，此項目之購物車採用session方法保存，故無CartDao，直接將操作方法寫在Cart類中
 * @author: Eker
 * @date: 2023/1/7 下午 03:59
 * @version: V1.0
 */
public class Cart {
    //創建一個Map類的購物車對象
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();


    /**
     * @titile: addItem
     * @description: 添加購物車商品項之方法
     * @param cartItem 欲添加之商品
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:12
     */
    public void addItem(CartItem cartItem) {
        //由商品編號確認購物車中之前是否有此商品
        CartItem item = items.get(cartItem.getId());

        if(item == null){
            //若購物車中之前沒有商品，則將商品添加至items內
            items.put(cartItem.getId(),cartItem);
        }else {
            //若購物車中之前已有此商品，則數量+1，總金額累加
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * @titile: deleteItem
     * @description: 刪除購物車商品
     * @param id 輸入欲刪除之購物車商品id
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:23
     */
    public void deleteItem(Integer id) {

        items.remove(id);
    }

    /**
     * @titile: clear
     * @description: 清空購物車
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:24
     */
    public void clear() {

        items.clear();
    }

    /**
     * @titile: updateCount
     * @description: 修改購物車商品數量
     * @param id 要修改的購物車商品id
     * @param count 修改後購物車商品數量
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:25
     */
    public void updateCount(Integer id,Integer count) {
        //由商品編號確認購物車中之前是否有此商品
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            //若購物車中之前已有此商品，則設置數量，總金額累加
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


    public Cart() {
    }


    /**
     * @titile: getTotalCount
     * @description: 購物車內總商品數量
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:28
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        //累加購物車內各商品項之商品數量
        for (Map.Entry <Integer,CartItem> entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        };
        //返回購物車總商品數量
        return totalCount;
    }


    /**
     * @titile: getTotalPrice
     * @description: 購物車內總金額
     * @param null
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 03:29
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        //累加購物車內各商品項之商品總金額
        for (Map.Entry <Integer,CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        };
        //返回購物車總商品金額
        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
