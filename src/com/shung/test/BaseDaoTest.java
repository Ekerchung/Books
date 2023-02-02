package com.shung.test;

import com.shung.dao.BaseDao;
import com.shung.dao.Imp.BookDaoImp;
import org.junit.Test;

/**
 * @Description:
 * @author: Eker
 * @date: 2022/11/18 下午 04:08
 * @version: V1.0
 */
public class BaseDaoTest {
BaseDao baseDao = new BaseDao() {
};
    @Test
    public void update() {
        String sql = "UPDATE t_order SET status = ? WHERE orderId = ?";
        baseDao.update(sql,1,"16738413137091");
    }

    @Test
    public void querySingle() {

    }

    @Test
    public void queryMulti() {
    }

    @Test
    public void queryScala() {
    }
}