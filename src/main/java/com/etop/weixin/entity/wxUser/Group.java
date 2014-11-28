package com.etop.weixin.entity.wxUser;

/**
 * 用户分组
 * @author Jeremie
 * Created by Jeremie on 2014/9/12.
 */
public class Group {

    /**
     * 组信息
     */
    private GroupParam group;

    public GroupParam getGroup() {
        return group;
    }

    public void setGroup(GroupParam group) {
        this.group = group;
    }

    public void setGroup(String id, String name) {
        this.group = new GroupParam(id, name);
    }
}
