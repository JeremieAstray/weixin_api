package com.etop.weixin.entity.message.response;

/**
 * 语音消息
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class Voice {
    /**
     * 通过上传多媒体文件，得到的id
     */
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
