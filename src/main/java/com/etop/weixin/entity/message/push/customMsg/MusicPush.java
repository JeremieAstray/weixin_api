package com.etop.weixin.entity.message.push.customMsg;

/**
 * 音乐信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class MusicPush extends CustomMsgBase {
    /**
     * 音乐
     */
    private Music music = new Music();

    public Music getMusic() {
        return music;
    }

    public void setTitle(String title) {
        this.music.setTitle(title);
    }

    public void setDescription(String description) {
        this.music.setDescription(description);
    }

    public void setMusicurl(String musicurl) {
        this.music.setMusicurl(musicurl);
    }

    public void setHqmusicurl(String hqmusicurl) {
        this.music.setHqmusicurl(hqmusicurl);
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.music.setThumb_media_id(thumb_media_id);
    }

    public void setMusic(Music music) {
        this.music = music;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Music implements Cloneable{
    /**
     * 音乐标题
     */
    private String title;
    /**
     * 音乐描述
     */
    private String description;
    /**
     * 音乐链接
     */
    private String musicurl;
    /**
     * 高品质音乐链接，wifi环境优先使用该链接播放音乐
     */
    private String hqmusicurl;
    /**
     * 缩略图的媒体ID
     */
    private String thumb_media_id;

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

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }

    public String getHqmusicurl() {
        return hqmusicurl;
    }

    public void setHqmusicurl(String hqmusicurl) {
        this.hqmusicurl = hqmusicurl;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
