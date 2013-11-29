package com.digitalglobe.librarymonitor.database;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
@XmlRootElement(name="request_table_data")
public class RequestItemTableData {

    private List<LibraryRequestItem> values;
    private TableData tableData;

    public RequestItemTableData() {
        values = Collections.<LibraryRequestItem>emptyList();
        tableData = new TableData();
    }


    public TableData getTableData() {
        return tableData;
    }

    public void setTableData(TableData tableData) {
        this.tableData = tableData;
    }

    public List<LibraryRequestItem> getValues()
    {
        return values;
    }

    public void setValues(List<LibraryRequestItem> values)
    {
       this.values = values;
    }

    @Override
    public String toString() {
        return "count:" + values.size();
    }
}
