package com.shung.service.imp;

import com.shung.bean.Book;
import com.shung.bean.Page;
import com.shung.dao.BaseDao;
import com.shung.dao.BookDao;
import com.shung.dao.Imp.BookDaoImp;
import com.shung.service.BookService;

import java.util.List;

/**
 * @Description: 圖書模塊的實現類
 * @author: Eker
 * @date: 2022/12/29 下午 05:14
 * @version: V1.0
 */
public class BookServiceImp implements BookService {
    private BookDao bookDao = new BookDaoImp();
    /**
     * @titile: addBook
     * @description: 調用BookDao實現添加圖書方法
     * @param book 添加之圖書資訊
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 06:48
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * @titile: deleteBookById
     * @description: 調用BookDao實現刪除圖書方法
     * @param id 要刪除之圖書id
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 06:49
     */
    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    /**
     * @titile: updateBook
     * @description: 調用BookDao實現修改圖書方法
     * @param book 修改後之圖書資訊
     * @return: null
     * @author: Eker
     * @date: 2023/2/2 下午 06:50
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * @titile: queryBookById
     * @description: 調用BookDao實現查詢圖書資訊(單個)
     * @param id 要查詢之圖書id
     * @return: Book 返回圖書資訊(單個)
     * @author: Eker
     * @date: 2023/2/2 下午 06:51
     */

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    /**
     * @titile: queryBooks
     * @description: 調用BookDao實現查詢圖書資訊(多個)
     * @param null
     * @return: List<Book> 返回圖書資訊(多個)
     * @author: Eker
     * @date: 2023/2/2 下午 06:52
     */
    @Override
    public List<Book> queryBooks() {

        return bookDao.queryBooks();
    }

    /**
     * @titile: page
     * @description: book類的分頁功能方法
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @return: Page<Book> 返回該分頁圖書資訊
     * @author: Eker
     * @date: 2023/2/2 下午 06:53
     */
    @Override
    public Page<Book> page(int pageNo , int pageSize) {
        Page page = new Page();
        //1. 設置pageSize
        page.setPageSize(pageSize);

        //3. 設置pageTotalCount
        //3.1 調用bookDao裡面的queryForPageTotalCount方法取得pageTotalCount
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //4. 設置pageTotal
        //4.1 利用公式求得pageTotal
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal = pageTotal + 1;
        }
        page.setPageTotal(pageTotal);

        //2. 設置pageNo
        //設置數據邊界的有效範圍
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }

        page.setPageNo(pageNo);
        //5. 設置items
        //5.1 調用bookDao裡面的queryForPageItems方法取得items
        List<Book> items = bookDao.queryForPageItems(pageNo,pageSize);
        page.setItems(items);

        return page;
    }

    /**
     * @titile: pageByPrice
     * @description: book類含自訂價格區間之分頁功能方法
     * @param pageNo 分頁頁碼
     * @param pageSize 分頁顯示商品之數量
     * @param min 自定義要查詢的商品最小金額
     * @param max 自定義要查詢的商品最大金額
     * @return: Page<Book> 返回含自訂價格區間該分頁圖書資訊
     * @author: Eker
     * @date: 2023/2/2 下午 06:55
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();
        //1. 設置pageSize
        page.setPageSize(pageSize);

        //3. 設置pageTotalCount
        //3.1 調用bookDao裡面的queryForPageTotalCountByPrice方法取得pageTotalCount
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //4. 設置pageTotal
        //4.1 利用公式求得pageTotal
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal = pageTotal + 1;
        }
        page.setPageTotal(pageTotal);

        //2. 設置pageNo
        //設置數據邊界的有效範圍
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }

        page.setPageNo(pageNo);
        //5. 設置items
        //5.1 調用bookDao裡面的queryForPageItemsByPrice方法取得items
        List<Book> items = bookDao.queryForPageItemsByPrice(pageNo,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
