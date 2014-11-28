package com.etop.weixin.entity;

/**
 * 基础类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class BaseWeixinEntity {
    /**
     * 推送对象
     */
    private String toUserName;
    /**
     * 信息来源
     */
    private String fromUserName;
    /**
     * 创建时间(秒为单位)
     */
    private Integer createTime;
    /**
     * 信息类型
     */
    private String msgType;

    private String funcFlag = "0";

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = Integer.parseInt(createTime);
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getFuncFlag() {
        return funcFlag;
    }

    public void setFuncFlag(String funcFlag) {
        this.funcFlag = funcFlag;
    }
}
