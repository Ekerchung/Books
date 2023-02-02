package com.shung.bean;

import java.math.BigDecimal;

/**
 * @Description: 訂單項OrderItem的容器
 * @author: Eker
 * @date: 2023/1/9 下午 04:11
 * @version: V1.0
 */
public class OrderItem {
    //訂單項id
    private Integer id;
    //訂單id
    private String orderId;
    //訂單項商品名稱
    private String name;
    //訂單項商品數量
    private Integer count;
    //訂單項商品單價
    private BigDecimal price;
    //訂單項商品總金額
    private BigDecimal totalPrice;
    //訂單項商品圖片位置
    private String imgPath = "static/img/default.jpg";

    public OrderItem() {
    }

    public OrderItem(Integer id, String orderId, String name, Integer count, BigDecimal price, BigDecimal totalPrice, String imgPath) {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
