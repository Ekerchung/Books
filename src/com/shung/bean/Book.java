package com.shung.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Description: book的javabean容器
 * @author: Eker
 * @date: 2022/12/27 上午 12:41
 * @version: V1.0
 */
public class Book {
    //圖書id
    private Integer b_id;
    //圖書名
    private String b_name;
    //圖書作者
    private String b_author;
    //圖書價格
    private BigDecimal b_price;
    //圖書銷量
    private Integer b_sales;
    //圖書庫存
    private Integer b_stoke;
    //圖書圖面位置
    private String b_imgPath = "static/img/default.jpg";

    public Book() {
    }

    public Book(Integer b_id, String b_name, String b_author, BigDecimal b_price, Integer b_sales, Integer b_stoke, String b_imgPath) {
        this.b_id = b_id;
        this.b_name = b_name;
        this.b_author = b_author;
        this.b_price = b_price;
        this.b_sales = b_sales;
        this.b_stoke = b_stoke;
        if(b_imgPath != null && !"".equals(b_imgPath)){
            this.b_imgPath = b_imgPath;
        }
    }

    public Integer getB_id() {
        return b_id;
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public BigDecimal getB_price() {
        return b_price;
    }

    public void setB_price(BigDecimal b_price) {
        this.b_price = b_price;
    }

    public Integer getB_sales() {
        return b_sales;
    }

    public void setB_sales(Integer b_sales) {
        this.b_sales = b_sales;
    }

    public Integer getB_stoke() {
        return b_stoke;
    }

    public void setB_stoke(Integer b_stoke) {
        this.b_stoke = b_stoke;
    }

    public String getB_imgPath() {
        return b_imgPath;
    }

    public void setB_imgPath(String b_imgPath) {
        if(b_imgPath != null && !"".equals(b_imgPath)){
            this.b_imgPath = b_imgPath;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(b_id, book.b_id) && Objects.equals(b_name, book.b_name) && Objects.equals(b_author, book.b_author) && Objects.equals(b_price, book.b_price) && Objects.equals(b_sales, book.b_sales) && Objects.equals(b_stoke, book.b_stoke) && Objects.equals(b_imgPath, book.b_imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b_id, b_name, b_author, b_price, b_sales, b_stoke, b_imgPath);
    }

    @Override
    public String toString() {
        return "Book{" +
                "b_id=" + b_id +
                ", b_name='" + b_name + '\'' +
                ", b_author='" + b_author + '\'' +
                ", b_price=" + b_price +
                ", b_sales=" + b_sales +
                ", b_stoke=" + b_stoke +
                ", b_imgPath='" + b_imgPath + '\'' +
                '}';
    }
}
