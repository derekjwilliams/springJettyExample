package com.digitalglobe.database;

import com.digitalglobe.BasicSpringTestCase;
import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LibraryRequestTest extends BasicSpringTestCase {

    @Resource
    LibraryRequest libraryRequest;

    @Test
    public void instantiation() throws Exception {
        assertThat(libraryRequest, notNullValue());
    }

}
