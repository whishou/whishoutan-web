package com.whishoutan.main.entity;


public class Page {
    private Integer currentPage =1;    //当前页
    private Integer totalPages;     //总页数
    private Integer totalCounts;    //总数据量
    private Integer pageSize = 5;       //每一页数据量
    private Integer nextPage;
    private Integer prePage;

    public Page() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalCounts % pageSize == 0 ? totalCounts / pageSize : totalCounts / pageSize + 1;
        //return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNextPage() {
        if (currentPage < totalPages)
        {
            nextPage = currentPage + 1;
        }
        else
        {
            nextPage = currentPage;
        }
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPrePage() {
        if (currentPage > 1)
        {
            prePage = currentPage - 1;
        }
        else
        {
            prePage = currentPage;
        }
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalCounts=" + totalCounts +
                ", pageSize=" + pageSize +
                ", nextPage=" + nextPage +
                ", prePage=" + prePage +
                '}';
    }
}
