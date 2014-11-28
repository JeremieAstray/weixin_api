package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.entity.message.push.template.TemplateBase;
import com.etop.weixin.utils.HttpUtil;

/**
 * 模板消息工具类
 * Created by Jeremie on 2014/9/9.
 */
public class TemplateMsgUtil {
    /**
     * 发送模板消息
     * @param templateBase 模板消息
     * @return 发送是否成功
     */
    public static boolean sendTemplateMsg(String accessToken,TemplateBase templateBase){
        String requestUrl = WeixinUtil.TEMPLATE_MSG_URL.replace("ACCESS_TOKEN",accessToken);
        String jsonMsg = JSON.toJSONString(templateBase);
        LogUtil.info("消息内容：" + jsonMsg);
        String response = HttpUtil.post(requestUrl, jsonMsg);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if(errorMsg != null){
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if(0 == errorCode){
                LogUtil.error("发送模板消息成功，errcode:" + errorCode);
                System.out.println("发送模板消息成功，errcode:" + errorCode);
                return true;
            }else{
                String errorMsgStr = errorMsg.getErrmsg();
                LogUtil.error("发送模板消息失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
                System.out.println("发送模板消息失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }
        return false;
    }
}
