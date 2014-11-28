package com.etop.weixin.utils.weixinUtils;


import com.etop.weixin.entity.common.WeixinAccount;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeremie
 * Created by Jeremie on 2014/9/3
 */
public class WeixinUtil {

    public static Map<String,WeixinAccount> accounts = new HashMap<>();

    // 获取access_token的接口地址（GET）
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * OAuth2.0引导关注者打开 用户同意授权，获取code页面url
     * 1.scope的设置为：snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面)
     * 2.redirect_uri：授权后重定向的回调链接地址，请使用urlencode对链接进行处理  方法再commonutil的urlEncodeUTF8()
     */
    public final static String FANS_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    // OAuth2.0通过code换取网页授权access_token
    public final static String OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // OAuth2.0刷新access_token
    public final static String REFRESH_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    // OAuth2.0拉取用户信息(需scope为 snsapi_userinfo)
    public final static String OAUTH2_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //OAuth2.0检验授权凭证(access_token)是否有效
    public final static String OAUTH2_CHECK="https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
    // 微信模板消息调用接口URL
    public final static String TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    // 主动发送客服消息url
    public final static String SEND_CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    // 生成临时二维码url
    public final static String TEMPORARY_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    // 生成永久二维码url
    public final static String PERMANENT_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    // 换取二维码url
    public final static String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    // 获取关注者列表url
    public final static String GET_USERLIST_URL = "https://api.weixin.qq.com/cgi-bin/User/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    // 开发者可通过OpenID来获取用户基本信息 url
    protected final static String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/User/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 通过OpenID获取查询用户所在分组url
    protected final static String GET_USERGROUPID_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";

    // 获取所有分组信息url
    public final static String GET_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    // 创建分组url
    public final static String CREATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    // 修改分组url
    public final static String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
    // 移动用户分组url
    public final static String MOVE_MEMBER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
    // 上传多媒体文件url
    public final static String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    // 下载多媒体文件url
    public final static String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    // 菜单创建（POST）
    public final static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 菜单查询（GET）
    public final static String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 菜单删除（GET）
    public final static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
/*    //长链接转短链接接口(POST) 需要授权？
    public final static String SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

    public static String longURL2ShortURL(String URL){
        String msg = String.format("{\"action\":\"long2short\",\"long_url\":\"%s\"}",URL);
        String response = HttpUtil.post(SHORT_URL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken()),msg);
        return response;
    }*/

    public static WeixinAccount getAccount(String id){
        if(accounts.containsKey(id)){
            return accounts.get(id);
        }else{
            //在数据库那里找，找不到就返回空
            return null;
        }
    }
}
