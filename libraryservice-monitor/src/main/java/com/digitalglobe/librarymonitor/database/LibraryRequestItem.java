package com.digitalglobe.librarymonitor.database;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@XmlRootElement(name="library_request_items")

public class LibraryRequestItem {
    private Integer id;
    private int libraryRequestId;
    private String retrievePath;
    private long sizeInBytes;
    private String lastModifiedBy;
    private Date lastModifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getLibraryRequestId() {
		return libraryRequestId;
	}

	public void setLibraryRequestId(Integer library_request_id) {
		this.libraryRequestId = library_request_id;
	}

    public String getRetrievePath()
    {
        return retrievePath;
    }

    public void setRetrievePath(String retrievePath)
    {
        this.retrievePath = retrievePath;
    }

    public long getSizeInBytes()
    {
        return sizeInBytes;
    }

    public void setSizeInBytes(long sizeInBytes)
    {
        this.sizeInBytes = sizeInBytes;
    }

    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy)
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime)
    {
        this.lastModifiedTime = lastModifiedTime;
    }


    @Override
    public String toString() {
        return "id:" + id + ",library_request_id:" + getLibraryRequestId();
    }

}
