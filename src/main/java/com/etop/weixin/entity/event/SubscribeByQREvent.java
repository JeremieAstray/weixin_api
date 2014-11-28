package com.etop.weixin.entity.event;

/**
 * 扫描带参数二维码事件
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class SubscribeByQREvent extends BaseWeixinEvent {

    /**
     * 事件KEY值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    public SubscribeByQREvent() {
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
