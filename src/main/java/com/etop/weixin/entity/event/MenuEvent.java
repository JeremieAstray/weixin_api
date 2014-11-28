package com.etop.weixin.entity.event;

/**
 * 自定义菜单事件
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class MenuEvent extends BaseWeixinEvent {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String eventKey;

    public MenuEvent() {
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
