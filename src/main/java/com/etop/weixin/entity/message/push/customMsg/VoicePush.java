package com.etop.weixin.entity.message.push.customMsg;

/**
 * 语音信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class VoicePush extends CustomMsgBase {
    /**
     * 语音信息
     */
    private Voice voice = new Voice();

    public Voice getVoice() {
        return voice;
    }

    public void setMedia_id(String media_id) {
        this.voice.setMedia_id(media_id);
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Voice implements Cloneable{
    /**
     * 发送的语音的媒体ID
     */
    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
