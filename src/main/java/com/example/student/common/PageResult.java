package com.example.student.common;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private List<T> list;
    private Long total;
    private Integer page;
    private Integer pageSize;

    public PageResult(List<T> list, long total, int page, int pageSize) {
        this.list = list;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
    }
}
