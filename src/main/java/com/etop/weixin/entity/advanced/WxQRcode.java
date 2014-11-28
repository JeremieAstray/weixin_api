package com.etop.weixin.entity.advanced;

/**
 * 临时二维码信息
 * @author Jeremie
 * Created by Jeremie on 2014/9/3.
 */
public class WxQRcode {

    /**
     * 获取的二维码ticket
     */
    private String ticket;
    /**
     * 二维码有效时间，以秒为单位， 最大不超过1800秒
     */
    private int expire_seconds;
    /**
     * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
