package com.etop.weixin.entity.event;

/**
 * Created by Jeremie on 2014/9/10.
 */
public class TemplateMsgEvent extends BaseWeixinEvent {

    /**
     * 消息ID
     */
    private String msgID;
    /**
     * 消息状态
     */
    private String status;

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
