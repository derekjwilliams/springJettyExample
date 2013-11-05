package com.digitalglobe.database;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="library_requests")
public class LibraryRequest {

    private Integer id;
    private String name;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name;
    }

}
