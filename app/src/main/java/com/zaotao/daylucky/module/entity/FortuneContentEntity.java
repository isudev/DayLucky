package com.zaotao.daylucky.module.entity;

public class FortuneContentEntity {
    private String title;
    private String text;
    private int img;
    private int lineImg;

    public FortuneContentEntity(String text) {
        this.text = text;
    }

    public FortuneContentEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getLineImg() {
        return lineImg;
    }

    public void setLineImg(int lineImg) {
        this.lineImg = lineImg;
    }
}
