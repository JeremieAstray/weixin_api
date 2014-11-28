package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.advanced.WxOauth;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.entity.advanced.WxSNSUser;
import com.etop.weixin.utils.HttpUtil;

/**
 * OAuth2授权工具
 * @author jeremie
 * Created by jeremie on 2014/9/21.
 */
public class OAuthUtil extends WeixinUtil{

    /**
     * 通过code换取网页授权access_token
     * @param appId 公众号的appid
     * @param appSecret 公众号的appsecret
     * @param code 第一步获取的code参数
     * @return
     */
    public static WxOauth getOauth(String appId, String appSecret, String code){
        WxOauth wxOauth = null;
        String requestUrl = OAUTH2_ACCESSTOKEN_URL.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        String response = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("获取网页授权凭证失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                wxOauth = JSON.parseObject(response, WxOauth.class);
                LogUtil.info(String.format("获取网页授权信息成功，openID为：" + wxOauth.getOpenid()));
            }
        } else {
            LogUtil.error("GET请求失败!");
        }
        return wxOauth;
    }


    /**
     * 刷新access_token
     * @param appId 公众号的appid
     * @param refreshToken 通过access_token获取到的refresh_token参数
     * @return
     */
    public static WxOauth refreshOauthToken(String appId, String refreshToken){
        WxOauth wxOauth = null;
        String requestUrl = REFRESH_ACCESSTOKEN_URL.replace("APPID", appId).replace("REFRESH_TOKEN", refreshToken);
        String response = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("刷新网页授权凭证失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                wxOauth = JSON.parseObject(response, WxOauth.class);
                LogUtil.info(String.format("刷新网页授权凭证成功，openID为：" + wxOauth.getOpenid()));
            }
        } else {
            LogUtil.error("GET请求失败!");
        }
        return wxOauth;
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * @param accessToken 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openId 用户的唯一标识
     * @return
     */
    public static WxSNSUser getSNSUser(String accessToken,String openId){
        WxSNSUser wxSNSUser = null;
        String requestUrl = OAUTH2_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String response =  HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("获取用户信息失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                wxSNSUser = JSON.parseObject(response, WxSNSUser.class);
                LogUtil.info(String.format("获取用户信息成功，openID为：" + wxSNSUser.getOpenid()));
            }
        } else {
            LogUtil.error("GET请求失败!");
        }
        return wxSNSUser;
    }

    /**
     * 检验授权凭证（access_token）是否有效
     * @param accessToken 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openId 用户的唯一标识
     * @return
     */
    public static boolean checkOauthToken(String accessToken, String openId){
        boolean result = false;
        String requestUrl = OAUTH2_CHECK.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String response = HttpUtil.get(requestUrl);
        ErrorMsg errorMsg = JSON.parseObject(response,ErrorMsg.class);
        if(errorMsg != null){
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if(0 == errorCode){
                result = true;
                LogUtil.info("检验授权凭证成功，errcode:" + errorCode);
            }else{
                String errorMsgStr = errorMsg.getErrmsg();
                result = false;
                LogUtil.error("检验授权凭证失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }
        return result;
    }

}
