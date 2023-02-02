package com.shung.service;

import com.shung.bean.Book;
import com.shung.bean.Page;

import java.util.List;

/**
 * @Description: 圖書模塊的service接口
 * @author: Eker
 * @date: 2022/12/29 下午 05:10
 * @version: V1.0
 */
public interface BookService {
    //添加圖書
    public void addBook(Book book);
    //刪除圖書
    public void deleteBookById(Integer id);
    //修改圖書資訊
    public void updateBook(Book book);
    //查詢圖書資訊(單個)
    public Book queryBookById(Integer id);
    //查詢圖書資訊(多個)
    public List<Book> queryBooks();
    //圖書的分頁方法
    Page<Book> page(int pageSize, int pageNo);
    //自定義圖書價格區間的分頁方法
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
