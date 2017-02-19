package com.example.sqlite;

/**
 * Created by yangt on 2017/2/18.
 */

public class Goods {
    private long id;
    private String goodsId;
    private String desc;
    private String price;
    private String picUrl;
    private int left;
    private long timestamp;

    public Goods(long id, String goodsId, String desc, String price, String picUrl, int left, long timestamp) {
        this.id = id;
        this.goodsId = goodsId;
        this.desc = desc;
        this.price = price;
        this.picUrl = picUrl;
        this.left = left;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
