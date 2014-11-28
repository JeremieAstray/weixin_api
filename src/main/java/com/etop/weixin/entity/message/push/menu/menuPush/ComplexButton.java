package com.etop.weixin.entity.message.push.menu.menuPush;

import java.util.ArrayList;

/**
 * 菜单按钮
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class ComplexButton extends Button {
    /**
     * 子菜单
     */
    private ArrayList<Button> sub_button;

    public ArrayList<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(ArrayList<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
