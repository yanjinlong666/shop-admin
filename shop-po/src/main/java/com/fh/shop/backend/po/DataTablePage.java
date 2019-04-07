package com.fh.shop.backend.po;

import java.io.Serializable;

public class DataTablePage implements Serializable {

    private static final long serialVersionUID = -7847494550665504378L;
    private int start;

    private int length;

    private int draw;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
