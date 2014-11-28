package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.utils.weixinUtils.MediaUtil;
import org.junit.Test;

import java.io.File;

/**
 * Created by Jeremie on 2014/9/3.
 */
public class MediaUtilTest {
    @Test
    public void testFile(){
        /*Map<String,Object> map = new HashMap<>();
        map.put("file",new File("C:/1.jpg"));
        //map.put("StringTest","helloworld");
        *//*System.out.println(HttpUtil.postEntityBuilder("http://192.168.1.194/Lab/test",map));*//*


        *//*map.put("id","201130720103");
        map.put("password","201130720103");*//*
        String url = "http://localhost:8080/plantserver/filesUpload";
        System.out.println(HttpUtil.postEntityBuilder("http://192.168.1.194/Lab/test",map));*/
        /*String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=" + ACCESS_TOKEN +"&type=thumb";
        WxMedia wxMedia = MediaUtil.uploadMedia(url, "image", "C:/12.jpg");
        System.out.println();*/
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        File file = MediaUtil.downloadMedia(weixinAccount.getAccess_token(),"9dAJKxKmjFvLzmecj0V8PqPjY110BpBW9zH7jZWp_LBnpQRgt2la5bcsPYXI6Zcy", "d:/");
        System.out.println(file.getName());
    }
}