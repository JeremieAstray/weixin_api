package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 图片信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class ImageResp extends BaseWeixinEntity {

    /**
     * 图片
     */
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
