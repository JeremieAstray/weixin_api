package com.etop.weixin.entity.message.push.customMsg;

/**
 * 视频信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class VideoPush extends CustomMsgBase {
    /**
     * 视频
     */
    private Video video = new Video();

    public Video getVideo() {
        return video;
    }

    public void setMedia_id(String media_id) {
        this.video.setMedia_id(media_id);
    }

    public void setThumb_media_id(String thumb_media_id){
        this.video.setThumb_media_id(thumb_media_id);
    }

    public void setTitle(String title) {
        this.video.setTitle(title);
    }

    public void setDescription(String description) {
        this.video.setDescription(description);
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Video implements Cloneable {
    /**
     * 发送的视频的媒体ID
     */
    private String media_id;
    /**
     * 缩略图的媒体ID
     */
    private String thumb_media_id;
    /**
     * 视频消息的标题
     */
    private String title;
    /**
     * 视频消息的描述
     */
    private String description;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}