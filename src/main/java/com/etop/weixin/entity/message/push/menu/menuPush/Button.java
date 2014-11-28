package com.etop.weixin.entity.message.push.menu.menuPush;

/**
 * 按钮基础类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public  class Button {
    /**
     * 菜单标题，不超过16个字节，子菜单不超过40个字节
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
