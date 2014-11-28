package com.etop.weixin.entity.message.request;

/**
 * 图片请求类
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class ImageMsg extends BaseWeixinMsg {

    /**
     * 图片链接
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;


    public ImageMsg() {
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
