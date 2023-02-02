package com.shung.test;

import com.shung.bean.Book;
import com.shung.bean.Page;
import com.shung.service.BookService;
import com.shung.service.imp.BookServiceImp;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: Eker
 * @date: 2022/12/29 下午 05:31
 * @version: V1.0
 */
public class BookServiceImpTest {
    private BookService bookService = new BookServiceImp();
    @Test
    public void addBook() {
        Book book = new Book(60,"test3","test3",new BigDecimal(123),123,222,"test");
        bookService.addBook(book);
    }


    @Test
    public void deleteBookById() {
        bookService.deleteBookById(32);
    }

    @Test
    public void updateBook() {
        Book book = new Book(31,"test3","test3",new BigDecimal(123),123,222,"test");
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(31);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()) {
            System.out.println(book);
        }

    }


    @Test
    public void page() {
        Page<Book> page = bookService.page(1,4);
        System.out.println(page);
    }
    @Test
    public void pageByPrice() {
        Page<Book> page = bookService.pageByPrice(1,4,10,300);
        System.out.println(page);
    }
}