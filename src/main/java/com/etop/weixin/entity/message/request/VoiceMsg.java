package com.etop.weixin.entity.message.request;

/**
 * 语音消息请求类
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class VoiceMsg extends BaseWeixinMsg {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String format;
    /**
     * 语音的文字内容
     */
    private String recognition;


    public VoiceMsg() {

    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}
