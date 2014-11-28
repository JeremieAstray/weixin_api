package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.common.ErrorMsg;
import com.etop.weixin.entity.wxUser.Group;
import com.etop.weixin.entity.wxUser.Groups;
import com.etop.weixin.utils.HttpUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用户分组管理
 *
 * @author Jeremie
 * Created by Jeremie on 2014/9/12.
 */
public class GroupUtil {

    /**
     * 创建分组
     * @param groupName 分组名
     * @return 分组
     */
    public static Group createGroup(String accessToken,String groupName) {
        Group group = null;
        String jsonStr = "{\"group\":{\"name\":\"NAME\"}}".replace("NAME", groupName);
        String requestUrl = WeixinUtil.CREATE_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.post(requestUrl, jsonStr);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("创建分组失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                group = JSON.parseObject(response, Group.class);
                LogUtil.info(String.format("创建分组成功，分组名：" + group.getGroup().getName() + "，分组ID：" + group.getGroup().getId()));
            }
        } else {
            LogUtil.error("POST请求失败!");
        }
        return group;
    }

    /**
     * 获取所有分组
     * @return 所有分组
     */
    public static Groups getAllGroups(String accessToken) {
        Groups groups = null;
        String requestUrl = WeixinUtil.GET_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("获取分组失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                groups = JSON.parseObject(response, Groups.class);
                LogUtil.info(String.format("获取分组成功，分组数目：" + groups.getGroups().size()));
            }
        } else {
            LogUtil.error("GET请求失败!");
        }
        return groups;
    }

    /**
     * 获取用户所在分组ID
     * @param openId 用户openID
     * @return 分组ID
     */
    public static String getUserGroupId(String accessToken,String openId) {
        String groupId = null;
        String jsonStr = "{\"openid\":\"OPENID\"}".replace("OPENID", openId);
        String requestUrl = WeixinUtil.GET_USERGROUPID_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.post(requestUrl, jsonStr);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            if (jsonObject.containsKey("errcode") && jsonObject.containsKey("errmsg")) {
                ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
                LogUtil.error("获取用户所在组ID失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            } else {
                groupId = jsonObject.getString("groupid");
                LogUtil.info(String.format("获取用户所在组ID成功，组id为：" + groupId));
            }
        } else {
            LogUtil.error("POST请求失败!");
        }
        return groupId;
    }

    /**
     * 修改分组名字
     * @param groupid 组ID
     * @param name 要修改成的名字
     * @return 修改是否成功
     */
    public static boolean updateGroupName(String accessToken,String groupid,String name) {
        boolean result = false;
        Group group = new Group();
        group.setGroup(groupid,name);
        String jsonStr = JSON.toJSONString(group);
        String requestUrl = WeixinUtil.UPDATE_GROUPS_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.post(requestUrl, jsonStr);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if (errorMsg != null) {
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if (0 == errorCode) {
                LogUtil.info("修改分组信息成功，errcode:" + errorCode);
                result =  true;
            } else {
                String errorMsgStr = errorMsg.getErrmsg();
                LogUtil.error("修改分组信息失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }else {
            LogUtil.error("POST请求失败!");
        }
        return result;
    }

    /**
     * 修改用户分组
     * @param openId 用户id
     * @param to_groupid 分组id
     * @return 修改是否成功
     */
    public static boolean changeUserGroup(String accessToken,String openId, String to_groupid) {
        boolean result = false;
        String jsonStr = "{\"openid\":\"OPENID\",\"to_groupid\":GROUPID}".replace("OPENID", openId).replace("GROUPID", to_groupid);
        String requestUrl = WeixinUtil.MOVE_MEMBER_URL.replace("ACCESS_TOKEN", accessToken);
        String response = HttpUtil.post(requestUrl, jsonStr);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if (errorMsg != null) {
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if (0 == errorCode) {
                LogUtil.info("移动用户分组成功，errcode:" + errorCode);
                result = true;
            } else {
                String errorMsgStr = errorMsg.getErrmsg();
                LogUtil.error("移动用户分组失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }else {
            LogUtil.error("POST请求失败!");
        }
        return result;
    }

    /**
     * 批量修改用户分组
     * @param openIDs 一堆用户id
     * @param to_groupid 分组id
     */
    public static void changeUserGroup(String accessToken,ArrayList<String> openIDs, String to_groupid) {
        int threadNumber = 3;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        for (String id : openIDs)
            service.execute(() -> changeUserGroup(accessToken,id, to_groupid));
        service.shutdown();
    }

    /*public static boolean deleteGroup(String groupId){
        String jsonStr = "{\"group\":{\"id\":\"ID\"}}".replace("ID",groupId);
        String requestUrl = WeixinUtil.DELETE_GROUPS_URL.replace("ACCESS_TOKEN",AccessTokenUtil.getAccessToken());
        String response = HttpUtil.post(requestUrl, jsonStr);
        ErrorMsg errorMsg = JSON.parseObject(response, ErrorMsg.class);
        if(errorMsg != null){
            int errorCode = Integer.parseInt(errorMsg.getErrcode());
            if(0 == errorCode){
                LogUtil.error("删除分组成功，errcode:" + errorCode);
                System.out.println("删除分组成功，errcode:" + errorCode);
                return true;
            }else{
                String errorMsgStr = errorMsg.getErrmsg();
                LogUtil.error("删除分组失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
                System.out.println("删除分组失败，errcode:" + errorCode + " errmsg:" + errorMsgStr);
            }
        }
        return false;
    }*/
}
