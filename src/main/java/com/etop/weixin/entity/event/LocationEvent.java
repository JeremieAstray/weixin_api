package com.etop.weixin.entity.event;

/**
 * 上报地理位置事件
 * @author Jeremie
 * Created by Jeremie on 2014/8/30.
 */
public class LocationEvent extends BaseWeixinEvent {

    /**
     * 地理位置纬度
     */
    private String latitude;
    /**
     * 地理位置经度
     */
    private String longitude;
    /**
     * 地理位置精度
     */
    private String precision;


    public LocationEvent() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
