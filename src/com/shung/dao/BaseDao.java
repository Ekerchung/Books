package com.shung.dao;

import com.shung.bean.User;
import com.shung.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 作為數據庫的CRUD操作的父類(通用的CRUD)
 * @author: Eker
 * @date: 2022/11/18 上午 11:21
 * @version: V1.0
 */
public abstract class BaseDao {
    QueryRunner queryRunner = new QueryRunner();
    /**
     * @titile: update
     * @description: 修改商品資訊
     * @param conn 連結數據庫的connection
     * @param sql sql語句
     * @param args sql語句中的參數
     * @return: int 返回修改數量，若修改失敗，返回值為-1
     * @author: Eker
     * @date: 2022/11/18 下午 12:41
     */

    //1.增刪改操作
    public int update(String sql , Object...args){
        Connection conn = JDBCUtils.getConntion();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @titile: querySingle
     * @description: 查詢單個商品對象
     * @param sql 查詢商品的sql代碼
     * @param clazz 查詢商品類的class
     * @param args  sql代碼中的參數
     * @return: T 商品類對象
     * @author: Eker
     * @date: 2023/2/2 下午 03:58
     */
    //2.查詢單行
    public <T> T querySingle(String sql, Class<T> clazz , Object...args){
        Connection conn = JDBCUtils.getConntion();
        T result = null;
        try {
            result = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * @titile: queryMulti
     * @description: 查詢多個商品
     * @param sql 查詢商品的sql代碼
     * @param clazz 查詢商品類的class
     * @param args  sql代碼中的參數
     * @return: List<T> 商品類的list對象
     * @author: Eker
     * @date: 2023/2/2 下午 04:00
     */
    //3.查詢多行
    public <T> List<T> queryMulti(String sql, Class<T> clazz , Object...args){
        Connection conn = JDBCUtils.getConntion();
        List<T> result = null;
        try {
            result = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    /**
     * @titile: queryScala
     * @description: 查詢scala數量
     * @param sql 查詢商品的sql代碼
     * @param args  sql代碼中的參數
     * @return: Object 返回Object類對象的數量
     * @author: Eker
     * @date: 2023/2/2 下午 04:01
     */
    //4.查詢單值
    public Object queryScala(String sql,Object...args){
        Connection conn = JDBCUtils.getConntion();
        Object result = null;
        try {
            result = queryRunner.query(conn, sql, new ScalarHandler(), args);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
