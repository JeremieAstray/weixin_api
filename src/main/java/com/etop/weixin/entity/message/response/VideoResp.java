package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 视频信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class VideoResp extends BaseWeixinEntity {
    /**
     * 视频信息
     */
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
