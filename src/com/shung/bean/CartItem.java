package com.shung.bean;

import java.math.BigDecimal;

/**
 * @Description: Cart內的CartItem的javabean容器
 * @author: Eker
 * @date: 2023/1/7 下午 04:00
 * @version: V1.0
 */
public class CartItem {

    //購物車項商品id
    private Integer id;
    //購物車項商品名稱
    private String name;
    //購物車項單價
    private BigDecimal price;
    //購物車項總金額
    private BigDecimal totalPrice;
    //購物車項數量
    private Integer count;
    //購物車項商品圖片位置
    private String imgPath = "static/img/default.jpg";

    public CartItem() {
    }

    public CartItem(Integer id, String name, BigDecimal price, BigDecimal totalPrice, Integer count, String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
