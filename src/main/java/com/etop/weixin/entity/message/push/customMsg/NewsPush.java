package com.etop.weixin.entity.message.push.customMsg;

import java.util.ArrayList;

/**
 * 图文信息推送类
 * @author Jeremie
 * Created by Jeremie on 2014/9/2.
 */
public class NewsPush extends CustomMsgBase {
    /**
     * 图文信息
     */
    private Item news = new Item();

    public Item getNews() {
        return news;
    }

    public void setNews(ArrayList<Articles> articles) {
        this.news.setArticles(articles);
    }

    public void setNews(Item news) {
        this.news = news;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Item implements Cloneable{
    /**
     * 图文信息列表
     */
    private ArrayList<Articles> articles;

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}