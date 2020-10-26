package com.zaotao.daylucky.module.entity;

import com.zaotao.daylucky.app.ColorManager;

public class SettingStyleEntity {
    private String name;
    private int color = ColorManager.colorsLineBg[0][1];

    public SettingStyleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
