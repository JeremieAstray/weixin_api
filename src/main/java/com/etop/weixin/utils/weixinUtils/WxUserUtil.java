package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.wxUser.WxUser;
import com.etop.weixin.entity.wxUser.WxUserList;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * 获取用户基本信息，获取所有用户ID
 * @author Jeremie
 * Created by Jeremie on 2014/9/3.
 */
public class WxUserUtil {

    /**
     * 获取单个用户的个人信息
     * @param openId 用户的openID
     * @return 用户信息，获取失败则返回null,错误信息在日志中
     */
    public static WxUser getUserInfo(String accessToken,String openId) {
        WxUser user = null;
        String requestUrl = WeixinUtil.GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        String responseMsg = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(responseMsg);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if(!(jsonObject.containsKey("errcode")&&jsonObject.containsKey("errmsg"))){
                user = JSON.parseObject(responseMsg,WxUser.class);
                if (StringUtils.isEmpty(user.getOpenid())) {
                    LogUtil.error("用户：" + openId + " 不存在");
                } else if (0 == user.getSubscribe()) {
                    LogUtil.error("用户：" + openId + " 已取消关注");
                }
            }else{
                ErrorMsg errorMsg = JSON.parseObject(responseMsg,ErrorMsg.class);
                LogUtil.error("获取基本个人信息失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            }
        }
        return user;
    }

    /**
     * 获取所有关注用户的openID
     * @return 用户openID的数组，获取失败则返回null,错误信息在日志中
     */
    public static ArrayList<String> getAllUserId(String accessToken) {
        return getAllUserId(accessToken,null);
    }

    private static ArrayList<String> getAllUserId(String accessToken,String openId) {
        ArrayList<String> list = new ArrayList<>();
        boolean isFirst = openId==null;
        String requestUrl = WeixinUtil.GET_USERLIST_URL.replace("ACCESS_TOKEN", accessToken);
        if(isFirst)
            requestUrl = requestUrl.replace("&next_openid=NEXT_OPENID","");
        else
            requestUrl = requestUrl.replace("NEXT_OPENID",openId);
        String responseMsg = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(responseMsg);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if(!(jsonObject.containsKey("errcode")&&jsonObject.containsKey("errmsg"))){
                WxUserList wxUserList= JSON.parseObject(responseMsg,WxUserList.class);
                if(wxUserList.getCount() != 0) {
                    list.addAll(wxUserList.getData());
                    while (wxUserList.getNextOpenId() != null && !"".equals(wxUserList.getNextOpenId())) {
                        list.addAll(getAllUserId(accessToken,wxUserList.getNextOpenId()));
                    }
                }
                if(isFirst)
                    LogUtil.info("一共获取到"+ list.size() +"条用户信息！");
            }else{
                ErrorMsg errorMsg = JSON.parseObject(responseMsg,ErrorMsg.class);
                LogUtil.error("获取所有用户ID信息失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            }
        }
        return list;
    }

}
