package com.etop.weixin.entity.event;

/**
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public enum EventEnum {
    //关注事件，取消关注事件，扫描带参数二维码事件（未关注），（已关注），上报地理位置事件，自定义菜单事件，模板消息推送事件
    SUBSCRIBE,UNSUBSCRIBE,SUBSCRIBEBYQR,SUBSCRIBEDBYQR,LOCATION,MENUCLICK,MENUVIEW,TEMPLATESENDJOBFINISH;
}
