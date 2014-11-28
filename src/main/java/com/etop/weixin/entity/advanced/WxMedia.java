package com.etop.weixin.entity.advanced;

/**
 * 微信媒体文件信息
 * @author Jeremie
 * Created by Jeremie on 2014/9/3
 */
public class WxMedia {

    /**
     * 媒体类型(image/voice/video/thumb)
     */
    private String type;
    /**
     * 媒体文件标识或缩略图的媒体文件标识
     */
    private String media_id;
    /**
     * 媒体文件上传的时间
     */
    private int created_at;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
}
