package com.digitalglobe.database;

import com.digitalglobe.RollbackTestCase;
import com.digitalglobe.librarymonitor.database.LibraryRequest;
import com.digitalglobe.librarymonitor.database.LibraryRequestDao;

import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LibraryRequestDaoTest extends RollbackTestCase {

    @Resource
    LibraryRequestDao dao;

    @Test
    public void setupIfNotYet() throws Exception {
        dao.setupIfNotYet();
    }

    @Test
    public void findAll() throws Exception {
        List<LibraryRequest> actual = dao.findAll(50, 0, "id", "desc");
        assertThat(actual.size(), is(equalTo(0)));
    }

}
