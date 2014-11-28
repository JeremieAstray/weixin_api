package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.utils.weixinUtils.WxUserUtil;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Jeremie on 2014/9/3.
 */
public class WxUserUtilTest {
    @Test
    public void testGetUserInfo(){
        /*WxUser wxUser = WxUserUtil.getUserInfo("oIuMNs2-L0ocEeIASG2v22YJDl-4");
        System.out.println("success");*/
    }

    @Test
    public void testGetUserId(){
        String APPID = "wxc8fc052c1d5c4d0e";
        String SECRET = "cc84a48d615167fe48ef9e3381919248";
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        ArrayList list = WxUserUtil.getAllUserId(weixinAccount.getAccess_token());
        System.out.println("success");

    }
}
