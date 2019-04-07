package com.fh.shop.backend.po.book;

import java.io.Serializable;

public class Book implements Serializable {

    private Integer id;

    private String bookName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
