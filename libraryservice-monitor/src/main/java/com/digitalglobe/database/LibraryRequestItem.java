package com.digitalglobe.database;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="library_request_items")

public class LibraryRequestItem {

    private Integer id;

    private Integer library_request_id;

    public Integer getId() {
        return id;
    }

//    public Integer getLibraryRequestId() {
//        return library_request_id;
//    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public void setLibraryRequestId(Integer library_request_id) {
//        this.library_request_id = library_request_id;
//    }

    @Override
    public String toString() {
        return "id:" + id + ",library_request_id:" + getLibrary_request_id();
    }

	public Integer getLibrary_request_id() {
		return library_request_id;
	}

	public void setLibrary_request_id(Integer library_request_id) {
		this.library_request_id = library_request_id;
	}

}
