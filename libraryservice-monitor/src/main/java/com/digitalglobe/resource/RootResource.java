package com.digitalglobe.resource;

import com.sun.jersey.api.view.Viewable;
import com.digitalglobe.database.Member;
import com.digitalglobe.database.MemberDao;
import com.digitalglobe.view.MembersViewModel;
import com.digitalglobe.database.LibraryRequest;
import com.digitalglobe.database.LibraryRequestDao;
import com.digitalglobe.view.LibraryRequestsViewModel;
import com.digitalglobe.database.LibraryRequestItem;
import com.digitalglobe.database.LibraryRequestItemDao;
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
    @Path("/")
    public String index() {
        log.info("index called!");
        return "Hello, JAX-RS(Jersey) with Spring!";
    }

    @GET
    @Path("/members")
    public Viewable members() throws Exception {
        MembersViewModel model = new MembersViewModel();
        model.setMembers(memberDao.findAll());
        for (Member member : model.getMembers()) {
            log.info("member:" + member.toString());
        }
        // Using JSP
        Viewable viewable = new Viewable("/members.jsp", model);
        return viewable;
        //return Response.ok(viewable).build();
    }
    
    @GET
    @Path("/members")
    @Produces({"application/json", "application/xml"})
    public List<Member> getMembers() {
    	return memberDao.findAll();
    }
    
    @GET
    @Path("/library_requests")
    @Produces({"application/json", "application/xml"})
    public List<LibraryRequest> getLibraryRequests() {
    	return libraryRequestDao.findAll();
    }
    @GET
    @Path("/library_request_items")
    @Produces({"application/json", "application/xml"})
    public List<LibraryRequestItem> getLibraryRequestItems() throws Exception {
    	return libraryRequestItemDao.findAll();
    }

    
    @GET
    @Path("/library_requests")
    public Object libraryrequests() throws Exception {
        LibraryRequestsViewModel model = new LibraryRequestsViewModel();
        model.setLibraryRequests(libraryRequestDao.findAll());
        for (LibraryRequest libraryRequestDao : model.getLibraryRequests()) {
            log.info("libraryRequestDao:" + libraryRequestDao.toString());
        }
        // Using JSP
        Viewable viewable = new Viewable("/libraryRequests.jsp", model);
        return Response.ok(viewable).build();
    }

    @GET
    @Path("/libraryrequestitems")
    public Object libraryrequestitems() throws Exception {
        LibraryRequestItemsViewModel model = new LibraryRequestItemsViewModel();
        model.setLibraryRequestItems(libraryRequestItemDao.findAll());
        for (LibraryRequestItem libraryRequestItemDao : model.getLibraryRequestItems()) {
            log.info("libraryRequestItemDao:" + libraryRequestItemDao.toString());
        }
        // Using JSP
        Viewable viewable = new Viewable("/libraryRequestItems.jsp", model);
        return Response.ok(viewable).build();
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
        // Using JSP
        return Response.ok(new Viewable("/post/input.jsp")).build();
    }

    @Resource
    Validator validator;

    @POST
    @Path("/post/submit")
    public Object postSubmit(@FormParam("id") String id,
                             @FormParam("password") String password) {

        log.info("@FormParam(\"id\"): " + id);
        log.info("@FormParam(\"password\"): " + password);

        // validation
        PostSubmitParams params = new PostSubmitParams(id, password);
        Set<ConstraintViolation<PostSubmitParams>> violations = validator.validate(params);
        if (!violations.isEmpty()) {
            log.debug("Validation failed : " + violations.size());
            for (ConstraintViolation<PostSubmitParams> v : violations) {
                log.debug(v.getPropertyPath().toString() + " " + v.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return "Posted: id=" + id + ",password=" + password;

    }

    public static class PostSubmitParams {
        public PostSubmitParams(String id, String password) {
            this.id = id;
            this.password = password;
        }

        @NotEmpty
        public String id;
        @NotEmpty
        public String password;
    }

}
