package com.ensta.myfilmlist.page;

import java.util.List;

public class Page<T> {
    private final int number;
    private final int size;
    private final long total;
    private final List<T> data;

    public Page(List<T> data, int size, int pageNumber, int total) {
        this.number = pageNumber;
        this.total = total;
        this.data = data;
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }
}
