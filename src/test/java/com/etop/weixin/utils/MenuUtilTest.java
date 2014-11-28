package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.entity.message.push.menu.menuPush.*;
import com.etop.weixin.utils.weixinUtils.MenuUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jeremie on 2014/9/12.
 */
public class MenuUtilTest {

    public static void main(String[] args){
        //MenuGet menu = MenuUtil.getCurrectMenu();
        //System.out.println(JSONObject.toJSONString(menu));
        //System.out.println("123");
        MenuPush menuPush = new MenuPush();
        ComplexButton complexButton1 = new ComplexButton();
        complexButton1.setName("信息处理");
        ClickButton clickButton1 = new ClickButton();
        clickButton1.setKey("text_recall");
        clickButton1.setName("文字信息");
        ClickButton clickButton2 = new ClickButton();
        clickButton2.setKey("pic_recall");
        clickButton2.setName("图片回复");
        ClickButton clickButton3 = new ClickButton();
        clickButton3.setKey("gps_recall");
        clickButton3.setName("地理信息");
        ClickButton clickButton4 = new ClickButton();
        clickButton4.setKey("link_recall");
        clickButton4.setName("链接信息");
        ClickButton clickButton5 = new ClickButton();
        clickButton5.setKey("voice_recall");
        clickButton5.setName("语音信息");
        complexButton1.setSub_button(new ArrayList<>(Arrays.asList(new Button[]{clickButton1, clickButton2, clickButton3, clickButton4, clickButton5})));
        ComplexButton complexButton2 = new ComplexButton();
        complexButton2.setName("信息推送");
        ClickButton clickButton6 = new ClickButton();
        clickButton6.setKey("text_push");
        clickButton6.setName("文字信息");
        ClickButton clickButton7 = new ClickButton();
        clickButton7.setKey("music_push");
        clickButton7.setName("音乐信息");
        ClickButton clickButton8 = new ClickButton();
        clickButton8.setKey("news_push");
        clickButton8.setName("图文信息");
        ViewButton viewButton = new ViewButton();
        viewButton.setName("百度");
        viewButton.setUrl("http://www.baidu.com/");
        complexButton2.setSub_button(new ArrayList<>(Arrays.asList(new Button[]{clickButton6,clickButton7,clickButton8,viewButton})));
        ClickButton clickButton9 = new ClickButton();
        clickButton9.setKey("click");
        clickButton9.setName("帮助");
        menuPush.setButton(new ArrayList<>(Arrays.asList(new Button[]{complexButton1,complexButton2,clickButton9})));
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        MenuUtil.createMenu(weixinAccount.getAccess_token(),menuPush);
        //MenuUtil.deleteMenu();
    }
}
