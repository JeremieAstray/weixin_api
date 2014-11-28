package com.etop.weixin.entity.message.push.customMsg;

/**
 * 图片信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class ImagePush extends CustomMsgBase {
    /**
     * 图片
     */
    private Image image = new Image();

    public Image getImage() {
        return image;
    }

    public void setMedia_id(String media_id) {
        this.image.setMedia_id(media_id);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

class Image implements Cloneable{
    /**
     * 发送的图片的媒体ID
     */
    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
