package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.entity.message.push.menu.menuGet.MenuGet;
import com.etop.weixin.entity.message.push.menu.menuPush.MenuPush;
import com.etop.weixin.utils.HttpUtil;

/**
 * 菜单工具类
 * @author Jeremie
 * Created by Jeremie on 2014/9/3
 */
public class MenuUtil {

    /**
     * 向微信推送新菜单
     * @param menuPush 菜单
     * @return 创建是否成功
     */
    public static boolean createMenu(String accessToken,MenuPush menuPush){
        boolean result = false;
        //创建菜单的URL
        String url = WeixinUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        String jsonStr = JSON.toJSONString(menuPush);
        ErrorMsg errorMsg = JSON.parseObject(HttpUtil.post(url, jsonStr),ErrorMsg.class);
        if(errorMsg != null){
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if(0 == errorCode){
                result = true;
            }else{
                String errorMsgStr = errorMsg.getErrmsg();
                result = false;
                LogUtil.error("创建菜单失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
                System.out.println("创建菜单失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }else {
            LogUtil.error("POST请求失败!");
        }
        return result;
    }

    /**
     * 获取当前菜单
     * @return 菜单
     */

    public static MenuGet getCurrectMenu(String accessToken){
        MenuGet menu = null;
        String requestUrl = WeixinUtil.GET_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("获取菜单失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                menu = JSON.parseObject(response, MenuGet.class);
                LogUtil.info(String.format("获取菜单成功：" + response));
            }
        } else {
            LogUtil.error("GET请求失败!");
        }
        return menu;
    }

    /**
     * 删除菜单
     * @return 是否删除成功
     */

    public static boolean deleteMenu(String accessToken){
        String requestUrl = WeixinUtil.DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.get(requestUrl);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if (errorMsg != null) {
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if (0 == errorCode) {
                LogUtil.error("删除菜单成功，errcode:" + errorCode);
                System.out.println("删除菜单成功，errcode:" + errorCode);
                return true;
            } else {
                String errorMsgStr = errorMsg.getErrmsg();
                LogUtil.error("删除菜单失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
                System.out.println("删除菜单失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }
        return false;
    }
}
