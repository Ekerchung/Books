package com.shung.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 訂單Order的容器
 * @author: Eker
 * @date: 2023/1/9 下午 04:06
 * @version: V1.0
 */
public class Order {
    //訂單id
    private String orderId;
    //創建訂單的時間
    private Date createTime;
    //訂單價格
    private BigDecimal price;
    //訂單狀態：0 未出貨；1 已出貨；2 已收貨
    private Integer status = 0;
    //訂單之用戶id
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
