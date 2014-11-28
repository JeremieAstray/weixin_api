package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.utils.weixinUtils.GroupUtil;
import com.etop.weixin.utils.weixinUtils.WxUserUtil;

import java.util.ArrayList;

/**
 * Created by Jeremie on 2014/9/12.
 */
public class GroupUtilTest {

    public static void main(String[] args){
        //GroupUtil.createGroup("test");
        //Groups groups = GroupUtil.getAllGroups();
        //System.out.println(GroupUtil.updateGroupName("103","test啊啊啊"));
        //System.out.println(GroupUtil.changeUserGroup("oY426t70SPfog5CPrdArTRjkG-pI","0"));
        //System.out.println(GroupUtil.getUserGroupId("oY426t70SPfog5CPrdArTRjkG-pI"));
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        ArrayList list = WxUserUtil.getAllUserId(weixinAccount.getAccess_token());
        GroupUtil.changeUserGroup(weixinAccount.getAccess_token(),list, "0");
        //System.exit(0);
    }
}
