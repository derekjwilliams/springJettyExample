package com.digitalglobe.librarymonitor.database;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@XmlRootElement(name="library_requests")
public class LibraryRequest {

    private Integer id;
    private String name;
    private String status;
    private Date requestTime;
    private int memberUserId = -1; // important to initialized to -1
    private int memberAccountId;
    private String requestOptions;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private String wpsStatusUrl;
    private String requestTimeString;
    private int percentComplete = -1;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getRequestTimestamp()
    {
        return requestTime;
    }

    public void setRequestTimestamp(Date requestTime)
    {
        this.requestTime = requestTime;
    }
    public String getRequestTimeString()
    {
        return requestTimeString;
    }

    public void setRequestTimeString()
    {
        final SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyy-MM-dd");
        timestampFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        requestTimeString = (this.requestTime == null) ? " " : timestampFormatter.format(this.requestTime);
    }

    public int getMemberUserId()
    {
        return memberUserId;
    }

    public void setMemberUserId(int memberUserId)
    {
        this.memberUserId = memberUserId;
    }

    public int getMemberAccountId()
    {
        return memberAccountId;
    }

    public void setMemberAccountId(int memberAccountId)
    {
        this.memberAccountId = memberAccountId;
    }

    public String getRequestOptions()
    {
        return requestOptions;
    }

    public void setRequestOptions(String requestOptions)
    {
        this.requestOptions = requestOptions;
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

    public String getWpsStatusUrl()
    {
        return wpsStatusUrl;
    }

    public void setWpsStatusUrl(String wpsStatusUrl)
    {
        this.wpsStatusUrl = wpsStatusUrl;
    }


    /**
     * Obtain the percent complete
     * @return -1 if there is no data in the DB about the percent complete, otherwise a value between
     *   1 and 100 showing the percentage complete.
     */
    public int getPercentComplete()
    {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete)
    {
        this.percentComplete = percentComplete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name + "status" + status;
    }
}
