package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 文本信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class TextResp extends BaseWeixinEntity {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String content;
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public TextResp() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}
