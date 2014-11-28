package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 音乐信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class MusicResp extends BaseWeixinEntity {
    /**
     * 音乐
     */
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
