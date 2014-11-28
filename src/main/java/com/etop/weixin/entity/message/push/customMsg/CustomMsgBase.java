package com.etop.weixin.entity.message.push.customMsg;

/**
 * 客服信息推送基础类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class CustomMsgBase implements Cloneable {
    /**
     * 目标用户
     */
    private String touser;
    /**
     * 信息类型
     */
    private String msgtype;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
