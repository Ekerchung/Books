package com.shung.test;

import com.shung.bean.Book;
import com.shung.dao.BookDao;
import com.shung.dao.Imp.BookDaoImp;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2022/12/29 上午 11:45
 * @version: V1.0
 */
public class BookDaoImpTest {
    private BookDao bookDao = new BookDaoImp();
    @Test
    public void addBook() {
        Book book = new Book(55,"test","test",new BigDecimal(123),123,222,"test");
        bookDao.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(30);
    }

    @Test
    public void updateBook() {
        Book book = new Book(42,"test2","test2",new BigDecimal(123),123,222,"test");
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(31);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for(Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,300));
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(2, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(1,4,10,300);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}