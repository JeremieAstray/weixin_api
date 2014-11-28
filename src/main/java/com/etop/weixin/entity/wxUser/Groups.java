package com.etop.weixin.entity.wxUser;

import java.util.ArrayList;

/**
 * 所有的组
 * @author Jeremie
 * Created by Jeremie on 2014/9/12.
 */
public class Groups {
    /**
     * 组列表
     */
    private ArrayList<GroupParam> groups;

    public ArrayList<GroupParam> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<GroupParam> groups) {
        this.groups = groups;
    }
}
