package com.etop.weixin.entity.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.utils.HttpUtil;
import com.etop.weixin.utils.weixinUtils.LogUtil;
import com.etop.weixin.utils.weixinUtils.WeixinUtil;


/**
 * 微信公众账号信息
 * @author Jeremie
 * Created by Jeremie on 2014/9/16.
 */
public class WeixinAccount {

    /**
     * 主键
     */
    private long id;
    /**
     * 公众账号名称
     */
    private String name;
    /**
     * 公众账号Token
     */
    private String token;
    /**
     * 公众微信号
     */
    private String number;
    /**
     * 公众账号原始Id
     */
    private String accountId;
    /**
     * 公众账号类型
     */
    private String type;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 公众账号描述
     */
    private String desc;
    /**
     * 公众账号APPID
     */
    private String appid;
    /**
     * 公众账号APPSECRET
     */
    private String appsecret;
    /**
     * 所属系统用户
     */
    private String userName;
    /**
     * 获取到的凭证
     */
    private String access_token;
    /**
     * 凭证有效时间，单位：秒
     */
    private int expires_in;
    /**
     * 凭证获取时间
     */
    private long acquireTokenTime = 0l;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public synchronized String getAccess_token() {
        if (this.access_token == null || System.currentTimeMillis()-this.acquireTokenTime > 7080000l) {
            String jsonstr = HttpUtil.get(WeixinUtil.ACCESS_TOKEN_URL.replace("APPID", this.appid).replace("APPSECRET", this.appsecret));
            JSONObject jsonObject = JSON.parseObject(jsonstr);
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(jsonstr, ErrorMsg.class);
                LogUtil.error("获取access_token失败errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
                this.access_token = null;
            } else {
                this.acquireTokenTime = System.currentTimeMillis();
                this.access_token = jsonObject.getString("access_token");
                this.expires_in = jsonObject.getIntValue("expires_in");
                LogUtil.info("获取access_token:" + this.access_token + "成功！");
            }
        }
        return this.access_token;
    }
}
