package com.digitalglobe.database;

import com.digitalglobe.BasicSpringTestCase;
import com.digitalglobe.librarymonitor.database.Member;

import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MemberTest extends BasicSpringTestCase {

    @Resource
    Member member;

    @Test
    public void instantiation() throws Exception {
        assertThat(member, notNullValue());
    }

}
