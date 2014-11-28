package com.etop.weixin.utils;

import com.etop.weixin.entity.advanced.WxQRcode;
import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.utils.weixinUtils.QRCodeUtil;
import org.junit.Test;

/**
 * Created by Jeremie on 2014/9/4.
 */
public class QRCodeUtilTest {
    @Test
    public void testCreateTempQRcode(){
        /*WxQRcode wxQRcode = QRCodeUtil.createTempQRcode(600, 123);
        System.out.println("success");*/
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        WxQRcode wxQRcode2 = QRCodeUtil.createPermQRcode(weixinAccount.getAccess_token(),123);
        System.out.println(QRCodeUtil.getQRPicUrl(wxQRcode2));;
    }
}
