package com.etop.weixin.entity.common;

/**
 * 返回信息类
 * @author Jeremie
 * Created by Jeremie on 2014/9/3.
 */
public class ErrorMsg {
    /**
     * 信息号码：http://mp.weixin.qq.com/wiki/index.php?title=全局返回码说明
     */
    private String errcode;
    /**
     * 信息
     */
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
