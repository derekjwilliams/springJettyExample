package com.digitalglobe.database;

import com.digitalglobe.RollbackTestCase;
import com.digitalglobe.librarymonitor.database.Member;
import com.digitalglobe.librarymonitor.database.MemberDao;

import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MemberDaoTest extends RollbackTestCase {

    @Resource
    MemberDao dao;

    @Test
    public void setupIfNotYet() throws Exception {
        dao.setupIfNotYet();
    }

    @Test
    public void findAll() throws Exception {
        List<Member> actual = dao.findAll();
        assertThat(actual.size(), is(equalTo(3)));
    }

}
