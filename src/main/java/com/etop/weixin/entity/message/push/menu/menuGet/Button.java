package com.etop.weixin.entity.message.push.menu.menuGet;

import java.util.ArrayList;

/**
 * 菜单按钮
 * @author Jeremie
 * Created by Jeremie on 2014/9/12.
 */
public class Button {
    private ArrayList<Button> sub_button;
    private String url;
    private String type;
    private String key;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(ArrayList<Button> sub_button) {
        this.sub_button = sub_button;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
