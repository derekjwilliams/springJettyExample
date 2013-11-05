package com.digitalglobe.database;

import com.digitalglobe.RollbackTestCase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LibraryRequestItemDaoTest extends RollbackTestCase {

    @Resource
    LibraryRequestItemDao dao;

    @Test
    public void setupIfNotYet() throws Exception {
        dao.setupIfNotYet();
    }

    @Test
    public void findAll() throws Exception {
        List<LibraryRequestItem> actual = dao.findAll();
        assertThat(actual.size(), is(equalTo(0)));
    }

}