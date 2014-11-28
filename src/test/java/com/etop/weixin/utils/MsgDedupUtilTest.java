package com.etop.weixin.utils;

import com.etop.weixin.utils.weixinUtils.MsgDedupUtil;
import org.junit.Test;

/**
 * Created by Jeremie on 2014/9/6.
 */
public class MsgDedupUtilTest {
    @Test
    public void handleKeyTest() throws InterruptedException {
        System.out.println(MsgDedupUtil.isRepetitive("123123"));
        Thread.sleep(10000);
        System.out.println(MsgDedupUtil.isRepetitive(new String("123123")));
        Thread.sleep(40000);
        System.out.println(MsgDedupUtil.isRepetitive("123", 123));
        Thread.sleep(30000);
        System.out.println(MsgDedupUtil.isRepetitive("123", 123));
        Thread.sleep(30000);
        System.out.println(MsgDedupUtil.isRepetitive("123", 123));
        Thread.sleep(30000);
        System.out.println(MsgDedupUtil.isRepetitive("123", 123));
        Thread.sleep(30000);
        System.out.println(MsgDedupUtil.isRepetitive("123", 123));
        Thread.sleep(10000);
        System.out.println(MsgDedupUtil.isRepetitive(new String("123123")));
        Thread.sleep(10000);
        System.out.println(MsgDedupUtil.isRepetitive(new String("123123")));
        Thread.sleep(10000);
        System.out.println(MsgDedupUtil.isRepetitive(new String("123123")));


    }
}
