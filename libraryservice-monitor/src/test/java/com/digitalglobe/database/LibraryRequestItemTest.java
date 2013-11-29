package com.digitalglobe.database;

import com.digitalglobe.BasicSpringTestCase;
import com.digitalglobe.librarymonitor.database.LibraryRequestItem;

import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LibraryRequestItemTest extends BasicSpringTestCase {

    @Resource
    LibraryRequestItem libraryRequestItem;

    @Test
    public void instantiation() throws Exception {
        assertThat(libraryRequestItem, notNullValue());
    }

}
