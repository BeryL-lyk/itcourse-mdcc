package com.itcourse.mdcc.result;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**--------------------------------------------------------
封装分页查询结果
--------------------------------------------------------**/
@Data
public class PageList<T> {

    //条数
    private Long total = 0L;

    //列表
    private List<T> rows = new ArrayList<>();


    public PageList() {
    }

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
