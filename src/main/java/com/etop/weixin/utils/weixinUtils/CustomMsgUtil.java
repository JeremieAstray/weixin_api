package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.entity.message.push.customMsg.CustomMsgBase;
import com.etop.weixin.utils.HttpUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发送客服消息工具
 * @author Jeremie
 * Created by Jeremie on 2014/9/3
 */
public class CustomMsgUtil {

    /**
     * 发送客服消息
     * @param customMsgBase 客服消息类
     * @return
     */
    public static boolean sendCustomMsg(String accessToken,CustomMsgBase customMsgBase){
        String jsonMsg = JSON.toJSONString(customMsgBase);
        LogUtil.info("消息内容：" + jsonMsg);
        boolean result = false;
        String requestUrl = WeixinUtil.SEND_CUSTOM_URL.replace("ACCESS_TOKEN",accessToken);
        //发送客服消息
        String response = HttpUtil.post(requestUrl, jsonMsg);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if(errorMsg != null){
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if(0 == errorCode){
                result = true;
                LogUtil.error("发送客服消息成功，errcode:" + errorCode);
                System.out.println("发送客服消息成功，errcode:" + errorCode);
            }else{
                String errorMsgStr = errorMsg.getErrmsg();
                result = false;
                LogUtil.error("发送客服消息失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
                System.out.println("发送客服消息失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }
        return result;
    }

    /**
     *
     * 任意一个用户发送成功都会返回成功，详细发送情况请看日志
     * @param customMsgBase
     * @return
     */

    public static void sendAllUserCustomMsg(String accessToken,CustomMsgBase customMsgBase){
        //获取所有用户id
        ArrayList<String> list = WxUserUtil.getAllUserId(accessToken);
        int threadNumber = 3;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        for(String id :list){
            service.execute(() -> {
                CustomMsgBase base = null;
                try {
                    base = (CustomMsgBase) customMsgBase.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                base.setTouser(id);
                sendCustomMsg(accessToken,base);
            });
        }
        service.shutdown();
/*
        int size = list.size();
        ArrayList<SendMsgThread> threads = new ArrayList<>();
        for(int i = 0;i<threadNumber;i++) {
            SendMsgThread thread = new SendMsgThread();
            try {
                thread.setPoint2PointBase((CustomMsgBase)customMsgBase.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            threads.add(thread);
        }
        for(int i = 0;i<size;i++)
            threads.get(i%threadNumber).OpenIds.add(list.get(i));
        for(SendMsgThread thread:threads){
            thread.start();
        }
        System.out.println("123456");*/
/*        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*
        for(String id :list){
            customMsgBase.setTouser(id);
            if(sendCustomMsg(customMsgBase)) result = true;

        }*/
    }

}
/*

class SendMsgThread extends Thread{

    public ArrayList<String> OpenIds = new ArrayList<>();
    private CustomMsgBase point2PointBase;

    public CustomMsgBase getPoint2PointBase() {
        return point2PointBase;
    }

    public void setPoint2PointBase(CustomMsgBase point2PointBase) {
        this.point2PointBase = point2PointBase;
    }

    @Override
    public void run() {
        for(String id :OpenIds){
            point2PointBase.setTouser(id);
            if(CustomMsgUtil.sendCustomMsg(point2PointBase)){
                CustomMsgUtil.result = true;
            };

        }
    }
}*/
