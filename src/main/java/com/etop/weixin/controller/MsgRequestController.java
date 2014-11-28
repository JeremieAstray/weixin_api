package com.etop.weixin.controller;

import com.etop.weixin.basic.controller.BaseController;
import com.etop.weixin.entity.advanced.WxOauth;
import com.etop.weixin.entity.advanced.WxSNSUser;
import com.etop.weixin.entity.wxUser.WxUser;
import com.etop.weixin.service.RequestService;
import com.etop.weixin.service.VerificationService;
import com.etop.weixin.utils.weixinUtils.OAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jeremie on 2014/9/11.
 */
@Controller
public class MsgRequestController extends BaseController {

    private String TOKEN = "weixin";

    @Autowired
    private RequestService requestService;
    @Autowired
    private VerificationService verificationService;

    @ResponseBody
    @RequestMapping(value = "/request.html",method = RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String dealRequest(@RequestBody String request) throws Exception{
        return requestService.responseMsg(request);
    }

    @ResponseBody
    @RequestMapping(value = "/request.html",method = RequestMethod.GET)
    public String dealCheck(String signature,String timestamp,String nonce,String echostr) throws Exception{
        return verificationService.verify(signature, timestamp, nonce, echostr, TOKEN);
    }

    @ResponseBody
    @RequestMapping(value = "/oauthNoInfo.html",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
    public String dealOAuthNoInfo(String code){
        WxOauth wxOauth = OAuthUtil.getOauth("wxc8fc052c1d5c4d0e","cc84a48d615167fe48ef9e3381919248",code);
        String wxOauthStr = requestService.printlnObject(wxOauth);
        return "恭喜你成功登陆,你的openID为：" + wxOauth.getOpenid() + "有效期为2小时\n" + wxOauthStr + "\n" ;
    }

    @ResponseBody
    @RequestMapping(value = "/oauthInfo.html",method = RequestMethod.GET,produces = "text/html; charset=utf-8")
    public String dealOAuthInfo(String code){
        WxOauth wxOauth = OAuthUtil.getOauth("wxc8fc052c1d5c4d0e","cc84a48d615167fe48ef9e3381919248",code);
        String wxOauthStr = requestService.printlnObject(wxOauth);
        WxSNSUser wxSNSUser = OAuthUtil.getSNSUser(wxOauth.getAccess_token(), wxOauth.getOpenid());
        String print = requestService.printlnObject(wxSNSUser);
        return "恭喜你成功登陆,你的openID为：" + wxOauth.getOpenid() + "有效期为2小时\n" + wxOauthStr + "\n" + print;

    }

}
