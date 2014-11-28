package com.etop.weixin.entity.message.push.menu.menuPush;

import java.util.ArrayList;

/**
 * 菜单推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class MenuPush {
    /**
     * 菜单
     */
    private ArrayList<Button> button;

    public ArrayList<Button> getButton() {
        return button;
    }

    public void setButton(ArrayList<Button> button) {
        this.button = button;
    }
}