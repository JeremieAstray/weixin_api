package com.etop.weixin.service;

import com.etop.weixin.basic.service.BaseService;
import com.etop.weixin.utils.Sha;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 处理微信Token验证的服务
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
@Service("VerificationService")
public class VerificationService extends BaseService {

    public String verify(String signature,String timestamp,String nonce,String echostr,String TOKEN){
        List<String> list = new ArrayList<>(3);
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);// 排序
        if(Sha.sha(list.get(0) + list.get(1) + list.get(2)).equals(signature))
            return echostr;
        else
            return "";
    }

}
