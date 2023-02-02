package com.shung.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.ha.selector.DataSourceSelectorFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description: 對數據庫進行連接及關閉資源
 * @author: Eker
 * @date: 2022/11/17 下午 05:25
 * @version: V1.0
 */
public class JDBCUtils {
    //靜態代碼塊:設置Druid數據池
    private static DruidDataSource ds;
    //使用ThreadLocal進行事務管理
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static{
        try {
            Properties pros = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            pros.load(is);

            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static Connection getConntion() {
        //獲取保存至ThreadLocal中druid數據池的連接
        Connection conn = conns.get();
        //判斷conn是否取得過連接，若無連接則從數據庫連接池中取得新連接
        if(conn == null){
            try {
                //從數據庫連接池中取得新連接
                conn = ds.getConnection();
                //保存至ThreadLocal中
                conns.set(conn);
                //取消自動提交
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose(){
        //取得ThreadLocal中保存的連接
        Connection conn = conns.get();
        //若conn不為空，表示之前使用此連接進行操作數據庫過
        if(conn != null){
            try {
                //提交事務
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //關閉連接
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要執行remove操作，因為Tomcat底層使用了線程池技術
        conns.remove();
    }

    public static void rollbackAndClose(){
        //取得ThreadLocal中保存的連接
        Connection conn = conns.get();
        //若conn不為空，表示之前使用此連接進行操作數據庫過
        if(conn != null){
            try {
                //回滾事務
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //關閉連接
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要執行remove操作，因為Tomcat底層使用了線程池技術
        conns.remove();
    }
}
