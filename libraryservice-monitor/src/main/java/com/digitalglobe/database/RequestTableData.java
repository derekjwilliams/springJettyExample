package com.digitalglobe.database;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
@XmlRootElement(name="request_table_data")
public class RequestTableData {

    private List<LibraryRequest> values;
    private TableData tableData;

    public RequestTableData() {
        values = Collections.<LibraryRequest>emptyList();
        tableData = new TableData();
    }


    public TableData getTableData() {
        return tableData;
    }

    public void setTableData(TableData tableData) {
        this.tableData = tableData;
    }

    public List<LibraryRequest> getValues()
    {
        return values;
    }

    public void setValues(List<LibraryRequest> values)
    {
       this.values = values;
    }

    @Override
    public String toString() {
        return "count:" + values.size();
    }
}
