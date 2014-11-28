package com.etop.weixin.entity.message.response;

import com.etop.weixin.entity.BaseWeixinEntity;

import java.util.ArrayList;

/**
 * 图文信息响应类
 * @author Jeremie
 * Created by Jeremie on 2014/9/1.
 */
public class NewsResp extends BaseWeixinEntity {
    /**
     * 图文消息个数，限制为10条以内
     */
    private Integer articleCount;
    /**
     * 图文信息
     */
    private ArrayList<Item> articles;

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public ArrayList<Item> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Item> articles) {
        this.articles = articles;
    }
}
