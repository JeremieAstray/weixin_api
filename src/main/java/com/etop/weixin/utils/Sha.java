package com.etop.weixin.utils;

import com.etop.weixin.utils.weixinUtils.LogUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class Sha {
    public static String sha(String inputText) {
        return encrypt(inputText);
    }

    /**
     * sha-1加密
     * @param inputText 要加密的内容
     * @return 加密后的值
     */
    private static String encrypt(String inputText) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance("sha-1");
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtil.error("加密失败", e);
        }
        return encryptText;
    }

    // 返回十六进制字符串
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }
}
