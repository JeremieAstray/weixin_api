package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 语音信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class VoiceResp extends BaseWeixinEntity {
    /**
     * 语音信息
     */
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
