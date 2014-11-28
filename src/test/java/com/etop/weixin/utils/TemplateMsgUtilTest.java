package com.etop.weixin.utils;

import com.etop.weixin.entity.common.WeixinAccount;
import com.etop.weixin.entity.message.push.template.TemplateBase;
import com.etop.weixin.entity.message.push.template.TemplateInfoEntity;
import com.etop.weixin.utils.weixinUtils.TemplateMsgUtil;

/**
 * Created by Jeremie on 2014/9/9.
 */
public class TemplateMsgUtilTest {
    public static void main(String[] args){
        String openId = "oY426t70SPfog5CPrdArTRjkG-pI"; //
        String templateID = "6XPxGqSgNj3jTYlEciK_G6NBweKoHOcUTALMrcn0Qpg";
        String url = "";
        String firstData="您好，您有新的待办任务";
        String keyword1 = "张三申请年假3天";
        String keyword2 = "待办";
        String remark = "请抽空处理";
        TemplateBase<TemplateInfoEntity> temp = new TemplateBase<>();
        TemplateInfoEntity data = new TemplateInfoEntity();
        data.setFirst(firstData, null);
        data.setKeyword1(keyword1,null);
        data.setKeyword2(keyword2,null);
        data.setRemark(remark, null);
        temp.setData(data);
        temp.setTemplate_id(templateID);
        temp.setTouser(openId);
        temp.setTopcolor("#FFFFFF");
        temp.setUrl(url);
        WeixinAccount weixinAccount = new WeixinAccount();
        weixinAccount.setAppid("wxc8fc052c1d5c4d0e");
        weixinAccount.setAppsecret("cc84a48d615167fe48ef9e3381919248");
        System.out.println(TemplateMsgUtil.sendTemplateMsg(weixinAccount.getAccess_token(),temp));
    }
}
