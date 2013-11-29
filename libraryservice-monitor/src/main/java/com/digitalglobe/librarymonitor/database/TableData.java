package com.digitalglobe.librarymonitor.database;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.util.*;

@XmlRootElement(name="table_data")
public class TableData {
    private Integer count;
    private Integer page;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    @Override
    public String toString() {
        return "count:" + count;
    } 
}
