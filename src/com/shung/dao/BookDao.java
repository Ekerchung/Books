package com.shung.dao;

import com.shung.bean.Book;

import java.util.List;

/**
 * @Description: book的Dao接口，
 * @author: Eker
 * @date: 2022/12/27 上午 12:40
 * @version: V1.0
 */
public interface BookDao {
    //添加圖書
    public int addBook(Book book);
    //刪除圖書
    public int deleteBookById(Integer id);
    //修改圖書
    public int updateBook(Book book);
    //查詢圖書(單個)
    public Book queryBookById(Integer id);
    //查詢圖書(多個)
    public List<Book> queryBooks();
    //查詢總頁面數量
    Integer queryForPageTotalCount();
    //查詢該分頁商品項
    List<Book> queryForPageItems(int pageNo, int pageSize);
    //自訂義金額範圍，查詢符合的商品總數量
    Integer queryForPageTotalCountByPrice(int min, int max);
    //自訂義金額範圍，查詢符合的該分頁商品項
    List<Book> queryForPageItemsByPrice(int pageNo, int pageSize, int min, int max);
}
