package com.bigfish.jpapaging.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lzp
 * @date 2020/1/6
 * 分页工具
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUtil<T> {

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页
     */
    private int number;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 总数据
     */
    private int totalElements;

    /**
     * 数据
     */
    private List<T> content = new ArrayList<>();




}
