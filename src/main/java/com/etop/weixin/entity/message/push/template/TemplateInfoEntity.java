package com.etop.weixin.entity.message.push.template;

/**
 * Created by Jeremie on 2014/9/10.
 */
public class TemplateInfoEntity {
    private TemplateValue first;
    private TemplateValue keyword1;
    private TemplateValue keyword2;
    private TemplateValue remark;

    public TemplateValue getFirst() {
        return first == null ? new TemplateValue() : first;
    }

    public void setFirst(String value, String color) {
        this.first = new TemplateValue().setValue(value).setColor(color);
    }

    public TemplateValue getKeyword1() {
        return keyword1 == null ? new TemplateValue() : keyword1;
    }

    public void setKeyword1(String value, String color) {
        this.keyword1 = new TemplateValue().setValue(value).setColor(color);
    }

    public TemplateValue getKeyword2() {
        return keyword2 == null ? new TemplateValue() : keyword2;
    }

    public void setKeyword2(String value, String color) {
        this.keyword2 = new TemplateValue().setValue(value).setColor(color);
    }

    public TemplateValue getRemark() {
        return remark == null ? new TemplateValue() : remark;
    }

    public void setRemark(String value, String color) {
        this.remark = new TemplateValue().setValue(value).setColor(color);
    }
}
