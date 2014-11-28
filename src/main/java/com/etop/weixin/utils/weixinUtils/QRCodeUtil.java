package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.advanced.WxQRcode;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.utils.HttpUtil;

/**
 * 二维码工具类
 *
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class QRCodeUtil {

    private final static String QRCODET_TEMP = "{\"expire_seconds\":%d,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
    private final static String QRCODEP_TEMP = "{\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";

    /**
     * 创建临时带参数二维码
     *
     * @param expireSeconds 二维码的有效时间，以秒为单位，最大不超过1800秒
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     *
     * @return 二维码类
     */
    public static WxQRcode createTempQRcode(String accessToken,int expireSeconds, int sceneId) {
        WxQRcode code = null;
        String requestUrl = WeixinUtil.TEMPORARY_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
        String jsonStr = String.format(QRCODET_TEMP,expireSeconds,sceneId);
        String response = HttpUtil.post(requestUrl, jsonStr);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("创建临时带参数二维码失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                code = JSON.parseObject(response, WxQRcode.class);
                LogUtil.info(String.format("创建临时带参数二维码成功 ticket:{%s},expire_seconds:{%s}", code.getTicket(), code.getExpire_seconds()));
            }
        }
        return code;
    }


    /**
     * 创建永久带参数二维码
     *
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     *
     * @return 二维码类
     */
    public static WxQRcode createPermQRcode(String accessToken,int sceneId) {
        WxQRcode code = null;
        String requestUrl = WeixinUtil.PERMANENT_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
        String jsonMsg = String.format(QRCODEP_TEMP, sceneId);
        String response = HttpUtil.post(requestUrl,jsonMsg);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("创建永久带参数二维码，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                code = JSON.parseObject(response, WxQRcode.class);
                LogUtil.info(String.format("创建永久带参数二维码 ticket:{%s},expire_seconds:{%s}", code.getTicket(), code.getExpire_seconds()));
            }
        }
        return code;
    }

    /**
     *
     * @param ticket 二维码ticket值
     * @return 获取二维码图片的链接
     */
    public static String getQRPicUrl(String ticket){
        String url = null;
        if(ticket!=null && !"".equals(ticket)){
            url = WeixinUtil.GET_QRCODE_URL.replace("TICKET", ticket);
        }
        return url;
    }

    /**
     *
     * @param wxQRcode 二维码类
     * @return 获取二维码图片的链接
     */
    public static String getQRPicUrl(WxQRcode wxQRcode){
        String url = null;
        if(wxQRcode!=null){
            url = getQRPicUrl(wxQRcode.getTicket());
        }
        return url;
    }

}