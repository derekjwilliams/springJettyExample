package com.digitalglobe.librarymonitor.resource;

import com.sun.jersey.api.view.Viewable;
import com.digitalglobe.view.MembersViewModel;
import com.digitalglobe.view.LibraryRequestsViewModel;
import com.digitalglobe.librarymonitor.database.LibraryRequest;
import com.digitalglobe.librarymonitor.database.LibraryRequestDao;
import com.digitalglobe.librarymonitor.database.LibraryRequestItem;
import com.digitalglobe.librarymonitor.database.LibraryRequestItemDao;
import com.digitalglobe.librarymonitor.database.Member;
import com.digitalglobe.librarymonitor.database.MemberDao;
import com.digitalglobe.librarymonitor.database.RequestItemTableData;
import com.digitalglobe.librarymonitor.database.RequestTableData;
import com.digitalglobe.librarymonitor.database.TableData;
import com.digitalglobe.view.LibraryRequestItemsViewModel;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@Path("/")
public class RootResource {
    private static final String DESC = "desc";
    private static final String ID_COL = "id";
    private static final int PAGE_SIZE = 50;
    private static final int START_PAGE = 1;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Context
    HttpServletRequest request;

    @Resource
    MemberDao memberDao;

    @Resource
    LibraryRequestDao libraryRequestDao;

    @Resource
    LibraryRequestItemDao libraryRequestItemDao;

    @GET
    @Path("/library_requests")
    @Produces({"application/json", "application/xml"})
    public RequestTableData getLibraryRequests(
            @QueryParam("rp") @DefaultValue("50") int limit, 
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("sortname") @DefaultValue("id") String sortName, 
            @QueryParam("sortorder") @DefaultValue("desc") String sortOrder ) 
    {
        final RequestTableData result = new RequestTableData();
        final TableData tableData = result.getTableData();
        tableData.setCount(libraryRequestDao.count());
        tableData.setPage(page);
        result.setValues(libraryRequestDao.findAll(limit, limit * (page - 1), sortName, sortOrder));
        return result;
    }

    @GET
    @Path("/library_request_items")
    @Produces({"application/json", "application/xml"})
    public RequestItemTableData getLibraryRequestItems(
            @QueryParam("rp") @DefaultValue("50") int limit, 
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("sortname") @DefaultValue("id") String sortName, 
            @QueryParam("sortorder") @DefaultValue("desc") String sortOrder ) 
    {
        final RequestItemTableData result = new RequestItemTableData();
        final TableData tableData = result.getTableData();
        tableData.setCount(libraryRequestDao.count());
        tableData.setPage(page);
        result.setValues(libraryRequestItemDao.findAll(limit, limit * (page - 1), sortName, sortOrder));
        return result;
    }

    @GET
    @Path("/echo")
    public Object echo(
            @QueryParam("param") @DefaultValue("default") String param) {
        log.info("request.getParameter(param): " + request.getParameter("param"));
        log.info("@QueryParam(\"param\"): " + param);
        return "Received: " + param;
    }

    @GET
    @Path("/post/input")
    public Object postInput() {
        return Response.ok(new Viewable("/post/input.jsp")).build();
    }

    @Resource
    Validator validator;
}
