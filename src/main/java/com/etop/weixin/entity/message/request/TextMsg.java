package com.etop.weixin.entity.message.request;

/**
 * 文本信息请求类
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class TextMsg extends BaseWeixinMsg {

    /**
     * 文本消息内容
     */
    private String content;


    public TextMsg() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
