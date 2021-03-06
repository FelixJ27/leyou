package com.leyou.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author Felix
 * @Description view object
 * @Date 2020/3/2 14:48
 * 面向对象面向卿，不负代码不负君
 */
@Data
public class PageResult<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 当前页数据
     */
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
