package com.digitalglobe.view;

import com.digitalglobe.database.Member;

import java.util.List;

public class MembersViewModel {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
