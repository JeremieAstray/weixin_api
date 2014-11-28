package com.etop.weixin.entity.wxUser;

import java.util.ArrayList;

/**
 * 关注者列表
 * @author Jeremie
 * Created by Jeremie on 2014/9/3.
 */
public class WxUserList {

    /**
     *关注该公众账号的总用户数
     */
    private int total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private int count;
    /**
     * 用户ID数据
     */
    private OpenId data;
    /**
     * 拉取列表的后一个用户的OPENID
     */
    private String nextOpenId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getData() {
        return data.getOpenid();
    }

    public void setData(OpenId data) {
        this.data = data;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }
}

class OpenId{
    /**
     * 列表数据，OPENID的列表
     */
    private ArrayList<String> openid;

    public ArrayList<String> getOpenid() {
        return openid;
    }

    public void setOpenid(ArrayList<String> openid) {
        this.openid = openid;
    }
}
