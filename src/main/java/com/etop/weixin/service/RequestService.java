package com.etop.weixin.service;

import com.etop.weixin.basic.service.BaseService;
import com.etop.weixin.entity.BaseWeixinEntity;
import com.etop.weixin.entity.event.*;
import com.etop.weixin.entity.message.request.*;
import com.etop.weixin.entity.message.response.*;
import com.etop.weixin.utils.weixinUtils.MsgDedupUtil;
import com.etop.weixin.utils.weixinUtils.WeixinUtil;
import com.etop.weixin.utils.weixinUtils.XMLUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 这是一个门面，需要分配下去给不同的请求进行处理
 *
 * @author Jeremie
 *         Created by Jeremie on 2014/8/30.
 */
@Service("RequestService")
public class RequestService extends BaseService {


    /**
     * 接入方法
     *
     * @param request 输入的xml
     */
    public String responseMsg(String request) {
        //是否回复信息的判断
        boolean flag = true;
        String result = " ";
        System.out.println(request);
        BaseWeixinEntity baseWeixinEntity = XMLUtil.XMLToObject(request);
        this.printlnObject(baseWeixinEntity);
        String event = baseWeixinEntity.getMsgType();
        BaseWeixinEntity reEntity = null;
        String reType = "text";
        if (!"event".equals(event)) {
            BaseWeixinMsg baseMsg = (BaseWeixinMsg) baseWeixinEntity;
            if (!MsgDedupUtil.isRepetitive(baseMsg.getMsgId())) {
                switch (event) {
                    case "text":
                        TextMsg textReq = (TextMsg) baseMsg;
                        switch (textReq.getContent()) {
                            case "你好":
                                TextResp textre = new TextResp();
                                textre.setContent("你更好!");
                                reEntity = textre;
                                break;
                            case "音乐":
                                MusicResp musicMsg = new MusicResp();
                                Music music = new Music();
                                music.setDescription("音乐");
                                music.setTitle("Epic Tribute Medley");
                                music.setMusicUrl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
                                music.setHQMusicUrl("http://jeremie-astray.eicp.net/Epic Tribute Medley.mp3");
                                music.setThumbMediaId("UDDU2YiPgtodNw1Rg91WOjVOnnHeiL4H6b3_827Hx4lE-NSuhNAcuSIOKJc228Pd");
                                musicMsg.setMusic(music);
                                reType = "music";
                                reEntity = musicMsg;
                                break;
                            case "图文信息":
                                NewsResp newsMsg = new NewsResp();
                                newsMsg.setArticleCount(4);
                                Item item = new Item();
                                item.setTitle("百度");
                                item.setDescription("百度一下，你就知道");
                                item.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item.setUrl("http://www.baidu.com/");
                                Item item1 = new Item();
                                item1.setTitle("百度一下，你就知道1");
                                item1.setDescription("百度一下，你就知道");
                                item1.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item1.setUrl("http://www.baidu.com/");
                                Item item2 = new Item();
                                item2.setTitle("百度一下，你就知道2");
                                item2.setDescription("百度一下，你就知道");
                                item2.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item2.setUrl("http://www.baidu.com/");
                                Item item3 = new Item();
                                item3.setTitle("百度一下，你就知道3");
                                item3.setDescription("百度一下，你就知道");
                                item3.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item3.setUrl("http://www.baidu.com/");
                                Item[] items = new Item[]{item, item1, item2, item3};
                                newsMsg.setArticles(new ArrayList<>(Arrays.asList(items)));
                                reType = "news";
                                reEntity = newsMsg;
                                break;
                            case "oauthnoinfo":
                                TextResp oauthnoinfo = new TextResp();
                                String oauthnoinfotext = WeixinUtil.FANS_GET_CODE.
                                        replace("APPID", "wx00c65ed92df1f6bf").
                                        replace("REDIRECT_URI", "http://jeremie-astray.eicp.net/oauthNoInfo.html").
                                        replace("SCOPE", "snsapi_base");
                                oauthnoinfo.setContent(oauthnoinfotext);
                                reEntity = oauthnoinfo;
                                break;
                            case "oauthinfo":
                                TextResp oauthinfo = new TextResp();
                                String oauthinfotext = WeixinUtil.FANS_GET_CODE.
                                        replace("APPID", "wx00c65ed92df1f6bf").
                                        replace("REDIRECT_URI", "http://jeremie-astray.eicp.net/oauthInfo.html").
                                        replace("SCOPE", "snsapi_base");
                                oauthinfo.setContent(oauthinfotext);
                                reEntity = oauthinfo;
                                break;
                            default:
                                TextResp textdefault = new TextResp();
                                textdefault.setContent("这里是来自冠鸿的公众平台消息，哈哈哈哈，欢迎光临~这是福利，没错。。。。就是这个福利hhhh");
                                reEntity = textdefault;
                                break;
                        }
                        break;
                    case "image":
                        TextResp textImage = new TextResp();
                        ImageMsg imageMsg = (ImageMsg) baseMsg;
                        textImage.setContent("你的照片好棒！！");
                        reEntity = textImage;
                        break;
                    case "voice":
                        TextResp textVoice = new TextResp();
                        VoiceMsg voiceMsg = (VoiceMsg) baseMsg;
                        textVoice.setContent("你说的是：\"" + voiceMsg.getRecognition() + "\"，你的声音真好听！");
                        reEntity = textVoice;
                        break;
                    case "location":
                        TextResp textLocation = new TextResp();
                        LocationMsg locationMsg = (LocationMsg) baseMsg;
                        textLocation.setContent("原来你在这里(" + locationMsg.getLocation_X() + ","
                                + locationMsg.getLocation_Y() + ")啊！！好，待会去捕抓你！！！");
                        reEntity = textLocation;
                        break;
                    case "link":
                        TextResp textLink = new TextResp();
                        LinkMsg linkMsg = (LinkMsg) baseMsg;
                        textLink.setContent("你的链接有病毒吧！");
                        reEntity = textLink;
                        break;
                    case "video":
                        TextResp textVideo = new TextResp();
                        VideoMsg videoMsg = (VideoMsg) baseMsg;
                        textVideo.setContent("哇，是视频哦！！");
                        reEntity = textVideo;
                        break;
                }
            } else
                flag = false;
        } else {
            BaseWeixinEvent baseEvent = (BaseWeixinEvent) baseWeixinEntity;
            if (!MsgDedupUtil.isRepetitive(baseEvent.getFromUserName(), baseEvent.getCreateTime())) {
                switch (baseEvent.getEventType()) {
                    case SUBSCRIBE:
                        SubscribeEvent subscribeEvent = (SubscribeEvent) baseEvent;
                        TextResp textSub = new TextResp();
                        textSub.setContent("感谢你的关注\\n回复任意信息会得到福利，回复你好。。。你自己试试看罗");
                        reEntity = textSub;
                        break;
                    case UNSUBSCRIBE:
                        SubscribeEvent unSubscribeEvent = (SubscribeEvent) baseEvent;
                        flag = false;
                        break;
                    case SUBSCRIBEBYQR:
                        SubscribeByQREvent subscribeByQREvent = (SubscribeByQREvent) baseEvent;
                        TextResp textSubByQR = new TextResp();
                        textSubByQR.setContent("感谢你的关注\\n回复任意信息会得到福利，回复你好。。。你自己试试看罗");
                        reEntity = textSubByQR;
                        break;
                    case SUBSCRIBEDBYQR:
                        SubscribeByQREvent subscribedByQREvent = (SubscribeByQREvent) baseEvent;
                        TextResp textSubedByQR = new TextResp();
                        textSubedByQR.setContent("你已经关注啦，谢谢关注哦!");
                        reEntity = textSubedByQR;
                        break;
                    case LOCATION:
                        LocationEvent locationEvent = (LocationEvent) baseEvent;
                        TextResp textLocation = new TextResp();
                        textLocation.setContent("监听发到你的方位,哔哔，在："
                                + locationEvent.getLatitude() + "," + locationEvent.getLongitude());
                        reEntity = textLocation;
                        break;
                    case MENUVIEW:
                        MenuEvent menuViewEvent = (MenuEvent) baseEvent;
                        flag = false;
                        break;
                    case TEMPLATESENDJOBFINISH:
                        TemplateMsgEvent templateMsgEvent = (TemplateMsgEvent) baseEvent;
                        System.out.println("接收到模板信息通知，通知内容为:" + templateMsgEvent.getStatus());
                        flag = false;
                        break;
                    case MENUCLICK:
                        MenuEvent menuClickEvent = (MenuEvent) baseEvent;
                        switch (menuClickEvent.getEventKey()) {
                            case "text_recall":
                                TextResp textRecall = new TextResp();
                                textRecall.setContent("请输入\"你好\"。");
                                reEntity = textRecall;
                                break;
                            case "pic_recall":
                                TextResp picRecall = new TextResp();
                                picRecall.setContent("请上传一张图片");
                                reEntity = picRecall;
                                break;
                            case "gps_recall":
                                TextResp gpsRecall = new TextResp();
                                gpsRecall.setContent("请发送你的位置信息");
                                reEntity = gpsRecall;
                                break;
                            case "link_recall":
                                TextResp linkRecall = new TextResp();
                                linkRecall.setContent("请发送一个链接");
                                reEntity = linkRecall;
                                break;
                            case "voice_recall":
                                TextResp voiceRecall = new TextResp();
                                voiceRecall.setContent("请跟我说话");
                                reEntity = voiceRecall;
                                break;
                            case "text_push":
                                TextResp textPush = new TextResp();
                                textPush.setContent("这里是来自冠鸿的公众平台消息，哈哈哈哈，欢迎光临~这是福利，没错。。。。就是这个福利hhhh");
                                reEntity = textPush;
                                break;
                            case "music_push":
                                MusicResp musicMsg = new MusicResp();
                                Music music = new Music();
                                music.setDescription("音乐");
                                music.setTitle("Epic Tribute Medley");
                                music.setMusicUrl("http://jeremieweb.nat123.net/Epic Tribute Medley.mp3");
                                music.setHQMusicUrl("http://jeremieweb.nat123.net/Epic Tribute Medley.mp3");
                                music.setThumbMediaId("UDDU2YiPgtodNw1Rg91WOjVOnnHeiL4H6b3_827Hx4lE-NSuhNAcuSIOKJc228Pd");
                                musicMsg.setMusic(music);
                                reType = "music";
                                reEntity = musicMsg;
                                break;
                            case "news_push":
                                NewsResp newsMsg = new NewsResp();
                                newsMsg.setArticleCount(4);
                                Item item = new Item();
                                item.setTitle("百度");
                                item.setDescription("百度一下，你就知道");
                                item.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item.setUrl("http://www.baidu.com/");
                                Item item1 = new Item();
                                item1.setTitle("百度一下，你就知道1");
                                item1.setDescription("百度一下，你就知道");
                                item1.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item1.setUrl("http://www.baidu.com/");
                                Item item2 = new Item();
                                item2.setTitle("百度一下，你就知道2");
                                item2.setDescription("百度一下，你就知道");
                                item2.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item2.setUrl("http://www.baidu.com/");
                                Item item3 = new Item();
                                item3.setTitle("百度一下，你就知道3");
                                item3.setDescription("百度一下，你就知道");
                                item3.setPicUrl("http://www.baidu.com/img/bdlogo.png");
                                item3.setUrl("http://www.baidu.com/");
                                Item[] items = new Item[]{item, item1, item2, item3};
                                newsMsg.setArticles(new ArrayList<>(Arrays.asList(items)));
                                reType = "news";
                                reEntity = newsMsg;
                                break;
                            case "click":
                                TextResp help = new TextResp();
                                help.setContent("帮助你妹啊！！！！！");
                                reEntity = help;
                                break;
                        }
                        break;
                }
            } else
                flag = false;
        }
        if (flag) {
            if(reEntity!=null) {
                reEntity.setCreateTime(System.currentTimeMillis() / 1000 + "");
                reEntity.setFromUserName(baseWeixinEntity.getToUserName());
                reEntity.setToUserName(baseWeixinEntity.getFromUserName());
                reEntity.setMsgType(reType);
                result = XMLUtil.ObjectToXML(reEntity);
                System.out.println(result);
            }
        }
        return result;
    }

    public <T> String printlnObject(T o) {
        StringBuffer print = new StringBuffer("");
        try {
            print.append( "================================================================================\n");
            print.append(o.getClass().getName() + "\n");
            Class<?> rec = o.getClass();
            ArrayList<Field> fields = new ArrayList<>();
            while (rec != Object.class) {
                fields.addAll(Arrays.asList(rec.getDeclaredFields()));
                rec = rec.getSuperclass();
            }
            for (Field field : fields) {
                Method method = o.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                print.append(field.getName() + " : " + method.invoke(o) + "\n");
            }
            print.append( "================================================================================\n");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(print.toString());
        return  print.toString();
    }
}
