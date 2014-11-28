package com.etop.weixin.entity.message.push.template;

/**
 * 模板子参数
 */
public class TemplateValue {
    /**
     * 内容
     */
	private String value = "";
    /**
     * 颜色
     */
	private String color = "";

    public String getValue() {
        return value;
    }

    public TemplateValue setValue(String value) {
        this.value = value;
        return this;
    }

    public String getColor() {
        return color;
    }

    public TemplateValue setColor(String color) {
        this.color = color;
        return this;
    }
}
