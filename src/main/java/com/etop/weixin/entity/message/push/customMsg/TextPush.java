package com.etop.weixin.entity.message.push.customMsg;

/**
 * 文本信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class TextPush extends CustomMsgBase {
    /**
     * 文本
     */
    private Text text = new Text();

    public Text getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setContent(text);
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Text implements Cloneable {
    /**
     * 文本内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
