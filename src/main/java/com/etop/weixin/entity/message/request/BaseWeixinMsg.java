package com.etop.weixin.entity.message.request;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * @author Jeremie
 * Created by Jeremie on 2014/9/6.
 */
public class BaseWeixinMsg extends BaseWeixinEntity {
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
