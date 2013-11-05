package com.digitalglobe.database;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="members")
public class Member {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name;
    }

}
