package com.etop.weixin.entity.message.push.menu.menuPush;

/**
 * 网页链接菜单类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class ViewButton extends Button {
    /**
     * 菜单的响应动作类型
     */
    private String type = "view";
    /**
     * 网页链接，用户点击菜单可打开链接，不超过256字节
     */
    private String url;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
