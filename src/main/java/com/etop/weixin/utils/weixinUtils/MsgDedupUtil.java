package com.etop.weixin.utils.weixinUtils;

import java.util.HashMap;

/**
 * 消息排除重复类
 * @author Jeremie
 * Created by Jeremie on 2014/9/6.
 */
public class MsgDedupUtil {

    private static HashMap<String,Boolean> characterMaps = new HashMap<>();

    public static boolean isRepetitive(String msgid){
        return handleKey(msgid);
    }

    public static boolean isRepetitive(String FromUserName,Integer createTime){
        return handleKey(FromUserName + createTime);
    }

    private static synchronized boolean handleKey(String key){
        if(characterMaps.containsKey(key)){
            characterMaps.replace(key,true);
            return true;
        }else{
            characterMaps.put(key,false);
            Thread thread = new Thread(new Runnable() {
                private boolean flag = true;
                private String thisKey = key;
                @Override
                public void run() {
                    try {
                        while(flag) {
                            characterMaps.replace(thisKey,false);
                            Thread.sleep(20000);
                            flag = characterMaps.get(thisKey);
                        }
                        characterMaps.remove(thisKey);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            return false;
        }
    }
}
