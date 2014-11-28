package com.etop.weixin.entity.wxUser;

/**
 * 组信息
 * @author Jeremie
 * Created by Jeremie on 2014/9/12.
 */
public class GroupParam {

    /**
     * 组id
     */
    private String id ;
    /**
     * 组名
     */
    private String name;
    /**
     * 组人数
     */
    private String count;

    public GroupParam() {
    }

    public GroupParam(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
