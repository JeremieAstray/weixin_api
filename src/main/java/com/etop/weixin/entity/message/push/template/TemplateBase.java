package com.etop.weixin.entity.message.push.template;

/**
 * 模版消息基类
 * 
 * @author Jeremie
 * 
 * @param <T>
 */
public class TemplateBase<T> {
    /**
     * 用户id
     */
	private String touser;
    /**
     * 模板id
     */
	private String template_id;
    /**
     * 模板点击链接
     */
	private String url;
    /**
     * 抬头颜色
     */
	private String topcolor;
    /**
     * 数据
     */
	private T data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
