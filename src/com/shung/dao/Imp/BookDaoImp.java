package com.shung.dao.Imp;

import com.shung.bean.Book;
import com.shung.dao.BaseDao;
import com.shung.dao.BookDao;
import com.shung.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @Description: 實現BookDao的增刪改查方法
 * @author: Eker
 * @date: 2022/12/28 上午 12:50
 * @version: V1.0
 */
public class BookDaoImp extends BaseDao implements BookDao {

    /**
     * @titile: addBook
     * @description: 新增book
     * @param book 欲新增之book
     * @return: 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2022/12/28 上午 01:25
     */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(b_name,b_author,b_price,b_sales,b_stoke,b_imgPath) values(?,?,?,?,?,?)";
        return update(sql,book.getB_name(),book.getB_author(),book.getB_price(),book.getB_sales(),
                book.getB_stoke(),book.getB_imgPath());

    }

    /**
     * @titile: deleteBookById
     * @description: 輸入id來刪除book
     * @param id 要刪除的圖書id
     * @return: 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2022/12/28 上午 01:28
     */
    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where b_id = ?";
        return update(sql,id);
    }


    /**
     * @titile: updateBook
     * @description: 修改book
     * @param book 修改後的book內容
     * @return: 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2022/12/28 上午 01:44
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set b_name = ?,b_author = ?,b_price = ?,b_sales = ?,b_stoke = ?,b_imgPath = ? where b_id = ?";
        return update(sql,book.getB_name(),book.getB_author(),book.getB_price(),book.getB_sales(),
                book.getB_stoke(),book.getB_imgPath(),book.getB_id());
    }
    /**
     * @titile: queryBookById
     * @description: 查詢圖書資訊(單個)
     * @param id 由圖書id查詢圖書資訊
     * @return: Book 返回圖書資訊
     * @author: Eker
     * @date: 2023/2/2 下午 04:15
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where b_id = ?";
        return querySingle(sql,Book.class,id);
    }

    /**
     * @titile: queryBooks
     * @description: 查詢圖書資訊(多個)
     * @return: List<Book> 返回圖書資訊(多個)
     * @author: Eker
     * @date: 2023/2/2 下午 04:16
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryMulti(sql,Book.class);
    }

    /**
     * @titile: queryForPageTotalCount
     * @description: 查詢總圖書數量
     * @return: Integer 返回總圖書數量
     * @author: Eker
     * @date: 2023/2/2 下午 04:18
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number pageTotalCount = (Number) queryScala(sql);
        return pageTotalCount.intValue();
    }

    /**
     * @titile: queryForPageItems
     * @description: 查詢該分頁之商品項
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @return: List<Book> 返回該分頁之商品項List
     * @author: Eker
     * @date: 2023/2/2 下午 04:24
     */
    @Override
    public List<Book> queryForPageItems(int pageNo, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        int beginNo = (pageNo - 1) * pageSize;
        List<Book> items = queryMulti(sql, Book.class, beginNo, pageSize);
        return items;
    }

    /**
     * @titile: queryForPageTotalCountByPrice
     * @description: 由自定義之查詢商品金額，查詢符合條件之總商品數量
     * @param min 自定義要查詢的商品最小金額
     * @param max 自定義要查詢的商品最大金額
     * @return: Integer 返回符合條件之總商品數量
     * @author: Eker
     * @date: 2023/2/2 下午 04:28
     */
    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where b_price between ? and ?";
        Number pageTotalCount = (Number) queryScala(sql,min,max);
        return pageTotalCount.intValue();
    }

    /**
     * @titile: queryForPageItemsByPrice
     * @description: 由自定義之查詢商品金額，查詢符合條件之商品項
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @param min 自定義要查詢的商品最小金額
     * @param max 自定義要查詢的商品最大金額
     * @return: List<Book> 返回符合條件之商品項List
     * @author: Eker
     * @date: 2023/2/2 下午 04:30
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int pageNo, int pageSize, int min, int max) {
        String sql = "select * from t_book where b_price between ? and ? order by b_price limit ?,?";
        int beginNo = (pageNo - 1) * pageSize;
        List<Book> items = queryMulti(sql, Book.class, min, max, beginNo, pageSize);
        return items;
    }
}
