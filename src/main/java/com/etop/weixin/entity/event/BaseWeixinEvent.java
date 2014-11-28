package com.etop.weixin.entity.event;

import com.etop.weixin.entity.BaseWeixinEntity;

/**
 * 基础事件
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class BaseWeixinEvent extends BaseWeixinEntity {
    /**
     * 消息类型
     */
    private String event;
    /**
     * 事件类型
     */
    private EventEnum eventType;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;

    }

    public EventEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventEnum eventType) {
        this.eventType = eventType;
    }
}
