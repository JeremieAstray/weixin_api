package com.etop.weixin.entity.message.push.menu.menuPush;

/**
 * 点击按钮
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class ClickButton extends Button {
    /**
     * 菜单的响应动作类型
     */
    private String type = "click";
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
