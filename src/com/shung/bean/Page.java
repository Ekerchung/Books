package com.shung.bean;

import java.util.List;

/**
 * @Description: 分頁的模型對象
 * @author: Eker
 * @date: 2023/1/3 下午 05:10
 * @version: V1.0
 */
public class Page<T> {
    //設置分頁顯示商品數量
    public static final Integer PAGE_SIZE = 4;
    //當前頁碼
    private Integer pageNo;
    //當前頁顯示數量
    private Integer pageSize = PAGE_SIZE;
    //總紀錄數
    private Integer pageTotalCount;
    //總頁碼
    private Integer pageTotal;
    //當前頁數據
    private List<T> items;
    //使用分頁功能之頁面url
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", pageTotal=" + pageTotal +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
