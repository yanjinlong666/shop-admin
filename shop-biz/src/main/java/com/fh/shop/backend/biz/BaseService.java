package com.fh.shop.backend.biz;

public class BaseService {

    protected String getCloumnData(String orderColumn) {
        return "columns[" + orderColumn + "][data]";
    }
}
