package com.digitalglobe.resource;

import com.digitalglobe.RollbackTestCase;
import com.digitalglobe.librarymonitor.resource.RootResource;

import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RootResourceTest extends RollbackTestCase {

    @Resource
    RootResource resource;

    @Test
    public void instantiation() throws Exception {
        assertThat(resource, notNullValue());
    }

    @Test
    public void index() throws Exception {
    }

}
