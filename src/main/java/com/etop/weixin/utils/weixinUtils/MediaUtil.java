package com.etop.weixin.utils.weixinUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etop.weixin.entity.advanced.WxMedia;
import com.etop.weixin.entity.common.ErrorMsg;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 微信专用上传多媒体工具类
 * @author Jeremie
 * Created by Jeremie on 2014/9/3.
 */
public class MediaUtil {

    /**
     * 上传多媒体文件
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param mediaFileUrl 多媒体文件的路径(如：C:/test/1.pg)
     * @return 多媒体文件类,上传失败则返回null,错误信息记录在日志中
     */
    public static WxMedia uploadMedia(String accessToken,String type, String mediaFileUrl) {
        WxMedia wxMedia = null;
        try {
            String url = WeixinUtil.UPLOAD_MEDIA_URL.replaceFirst("ACCESS_TOKEN", accessToken).replaceFirst("TYPE",type);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            FileBody fileBody = new FileBody(new File(mediaFileUrl));
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntityBuilder.addPart("descript",
                    new StringBody("file1." + FilenameUtils.getExtension(mediaFileUrl), ContentType.create("text/plain", "UTF-8")));
            multipartEntityBuilder.addPart("file", fileBody);
            HttpEntity entity = multipartEntityBuilder.build();
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            String jsonStr = null;
            if (httpEntity != null) {
                jsonStr = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            }
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            if(jsonObject.containsKey("errcode")&&jsonObject.containsKey("errmsg")){
                ErrorMsg errorMsg = JSON.parseObject(jsonStr,ErrorMsg.class);
                LogUtil.error("上传失败，errcode:" + errorMsg.getErrcode() + " errmsg:" + errorMsg.getErrmsg());
            }else {
                wxMedia = JSON.parseObject(jsonStr, WxMedia.class);
            }
            return wxMedia;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error("上传媒体文件失败",e);
        }
        return null;
    }

    /**
     * 下载多媒体文件
     * @param mediaId 媒体文件Id
     * @param savePath 文件保存路径
     * @return 返回多媒体文件
     */
    public static File downloadMedia(String accessToken,String mediaId,String savePath) {
        try{
            String url = WeixinUtil.DOWNLOAD_MEDIA_URL.replaceFirst("ACCESS_TOKEN",accessToken).replace("MEDIA_ID", mediaId);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            HttpEntity entity = httpResponse.getEntity();
            if(statusLine.getStatusCode() == 200 && entity != null){
                if(!savePath.endsWith("/")) savePath +="/";
                String ContentDisposition = "";
                Header[] headers = httpResponse.getAllHeaders();
                for(Header header:headers){
                    if ("Content-disposition".equalsIgnoreCase(header.getName())){
                        ContentDisposition =  header.getValue();
                        break;
                    }
                }
                String fileName = ContentDisposition.replace("attachment; filename=","").replaceAll("\"","");
                File file = new File(savePath + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                entity.writeTo(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return file;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            LogUtil.error("连接失败",e);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error("文件写入失败",e);
        }
        return null;
    }

    /**
     * 下载多媒体文件
     * @param wxMedia 媒体类
     * @param savePath 文件保存路径
     * @return 返回多媒体文件
     */
    public static File downloadMedia(String accessToken,WxMedia wxMedia,String savePath) {
        return downloadMedia(accessToken,wxMedia.getMedia_id(),savePath);
    }
}
