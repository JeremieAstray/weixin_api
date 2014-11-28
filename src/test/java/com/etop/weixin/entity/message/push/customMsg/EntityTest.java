package com.etop.weixin.entity.message.push.customMsg;

import com.etop.weixin.entity.BaseWeixinEntity;
import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.entity.message.response.*;
import com.etop.weixin.utils.weixinUtils.CustomMsgUtil;
import com.etop.weixin.utils.weixinUtils.MediaUtil;
import com.etop.weixin.utils.weixinUtils.WxUserUtil;
import com.etop.weixin.utils.weixinUtils.XMLUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jeremie on 2014/9/2.
 */
public class EntityTest {

    @Test
    public void testTextPush(){
        /*TextPush textPush = new TextPush();
        textPush.setText("hello");
        textPush.setMsgtype("text");
        textPush.setTouser("123");
        System.out.println(JSON.toJSONString(textPush));

        VideoPush videoPush = new VideoPush();
        videoPush.setMsgtype("video");
        videoPush.setDescription("DESCRIPTION");
        videoPush.setTouser("OPENID");
        videoPush.setThumb_media_id("MEDIA_ID");
        videoPush.setMedia_id("MEDIA_ID");
        videoPush.setTitle("TITLE");
        System.out.println(JSON.toJSONString(videoPush));*/

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
        newsPush.setTouser("123");
        System.out.println(JSON.toJSONString(newsPush));*/
        /*BaseWeixinEntity reEntity = null;
        NewsResp newsMsg = new NewsResp();
        newsMsg.setArticleCount(4);
        com.etop.weixin.entity.message.response.Item item = new com.etop.weixin.entity.message.response.Item();
        item.setTitle("百度");
        item.setDescription("百度一下，你就知道");
        item.setPicUrl("http://www.baidu.com/img/bdlogo.png");
        item.setUrl("http://www.baidu.com/");
        com.etop.weixin.entity.message.response.Item item1 = new com.etop.weixin.entity.message.response.Item();
        item1.setTitle("百度一下，你就知道1");
        item1.setDescription("百度一下，你就知道");
        item1.setPicUrl("http://www.baidu.com/img/bdlogo.png");
        item1.setUrl("http://www.baidu.com/");
        com.etop.weixin.entity.message.response.Item item2 = new com.etop.weixin.entity.message.response.Item();
        item2.setTitle("百度一下，你就知道2");
        item2.setDescription("百度一下，你就知道");
        item2.setPicUrl("http://www.baidu.com/img/bdlogo.png");
        item2.setUrl("http://www.baidu.com/");
        com.etop.weixin.entity.message.response.Item item3 = new com.etop.weixin.entity.message.response.Item();
        item3.setTitle("百度一下，你就知道3");
        item3.setDescription("百度一下，你就知道");
        item3.setPicUrl("http://www.baidu.com/img/bdlogo.png");
        item3.setUrl("http://www.baidu.com/");
        com.etop.weixin.entity.message.response.Item[] items = new com.etop.weixin.entity.message.response.Item[]{item, item1, item2, item3};
        newsMsg.setArticles(new ArrayList<>(Arrays.asList(items)));
        String reType = "news";*/
        BaseWeixinEntity reEntity = null;
        MusicPush resp = new MusicPush();
        Music music = new Music();
        resp.setMusic(music);
        music.setDescription("音乐");
        music.setTitle("Epic Tribute Medley");
        music.setMusicurl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
        music.setHqmusicurl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
        music.setThumb_media_id("HLCOrlPnD1XmjvqZCNNE6QUeR0izcSgTgLJaNlBB_Yih0Hm_oRp3Vj-NgBQM9WAB");
        resp.setTouser("oIuMNs3_Ty3pv7k2Hy8MpULgHuAY");
        resp.setMsgtype("music");
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        CustomMsgUtil.sendCustomMsg(weixinAccount.getAccess_token(),resp);
    }
}
