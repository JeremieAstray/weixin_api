package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.entity.message.push.customMsg.CustomMsgBase;
import com.etop.weixin.entity.message.push.customMsg.TextPush;
import com.etop.weixin.utils.weixinUtils.CustomMsgUtil;
import com.etop.weixin.utils.weixinUtils.WxUserUtil;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Jeremie on 2014/9/2.
 */

public class HttpUtilTest {
    //@Test
    public void testPost() {
        //map.put("id","administrator");
        //map.put("password","SCAUINFO@940");
        //String html = HttpUtil.post("http://localhost/internrecord/php/login.php",map,false);
        //String jsons = "{\"touser\":\"oIuMNs3_Ty3pv7k2Hy8MpULgHuAY\",\"msgtype\":\"text\",\"text\":{\"content\":\"这是信息推送哦~\"}}";
        /*TextPush textPush = new TextPush();
        textPush.setText("这是即时的信息推送，hello");
        textPush.setMsgtype("text");
        textPush.setTouser("oIuMNs3_Ty3pv7k2Hy8MpULgHuAY");*/
        /*NewsPush newsPush = new NewsPush();
        Articles articles = new Articles();
        articles.setTitle("百度");
        articles.setDescription("百度一下，你就知道");
        articles.setPicurl("http://www.baidu.com/img/bdlogo.png");
        articles.setUrl("http://www.baidu.com/");
        Articles articles1 = new Articles();
        articles1.setTitle("百度一下，你就知道1");
        articles1.setDescription("百度一下，你就知道");
        articles1.setPicurl("http://www.baidu.com/img/bdlogo.png");
        articles1.setUrl("http://www.baidu.com/");
        Articles articles2 = new Articles();
        articles2.setTitle("百度一下，你就知道2");
        articles2.setDescription("百度一下，你就知道");
        articles2.setPicurl("http://www.baidu.com/img/bdlogo.png");
        articles2.setUrl("http://www.baidu.com/");
        Articles articles3 = new Articles();
        articles3.setTitle("百度一下，你就知道3");
        articles3.setDescription("百度一下，你就知道");
        articles3.setPicurl("http://www.baidu.com/img/bdlogo.png");
        articles3.setUrl("http://www.baidu.com/");
        Articles[] articleses = new Articles[]{articles,articles1,articles2,articles3};
        newsPush.setNews(new ArrayList<>(Arrays.asList(articleses)));
        newsPush.setMsgtype("news");
        newsPush.setTouser("oIuMNs3_Ty3pv7k2Hy8MpULgHuAY");*/
        /*MusicPush music = new MusicPush();
        music.setDescription("音乐");
        music.setTitle("Epic Tribute Medley");
        music.setMusicurl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
        music.setHqmusicurl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
        music.setTouser("oIuMNs3_Ty3pv7k2Hy8MpULgHuAY");
        music.setMsgtype("music");
        music.setThumb_media_id("MTXfbhDV0mNvIAD4nbcp4u6fFLzzy6P6SQ-CusY8nacV0k-6d04qynAuu8pzCC5a");
        CustomMsgUtil.sendCustomMsg(music);*/
        //System.out.println(html);
        //群发信息测试
        TextPush textPush = new TextPush();
        textPush.setText("多线程群发信息:你家缺冠鸿吗？会写程序的那种？------小尾巴：这是由冠鸿写的群发程序所发送的信息~");
        textPush.setMsgtype("text");
        //textPush.setTouser("oIuMNs3_Ty3pv7k2Hy8MpULgHuAY");
        //textPush.setTouser("123465");
        CustomMsgBase customMsgBase = textPush;
        /*System.out.println(JSON.toJSONString(customMsgBase));
        //CustomMsgUtil.sendCustomMsg(customMsgBase);
        CustomMsgUtil.sendAllUserCustomMsg(textPush);*/
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        ArrayList<String> list = WxUserUtil.getAllUserId(weixinAccount.getAccess_token());
        for(String id :list){
            ExecutorService service = Executors.newFixedThreadPool(10);
            service.execute(() -> {
                CustomMsgBase base = null;
                try {
                    base = (CustomMsgBase) customMsgBase.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                base.setTouser(id);
                CustomMsgUtil.sendCustomMsg(weixinAccount.getAccess_token(),base);
            });

            service.shutdown();
        }
        //CustomMsgUtil.sendCustomMsg(music);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        TextPush textPush = new TextPush();
        textPush.setText("多线程群发信息测试（这条信息需要在48小时内用户有回复才能群发成功。）:你家缺冠鸿吗？会写程序的那种？------小尾巴：这是由冠鸿写的群发程序所发送的信息~");
        textPush.setMsgtype("text");
        CustomMsgUtil.sendAllUserCustomMsg(weixinAccount.getAccess_token(),textPush);

    }
}
