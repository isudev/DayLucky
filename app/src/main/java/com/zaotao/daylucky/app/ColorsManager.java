package com.zaotao.daylucky.app;

import android.graphics.Color;

import com.zaotao.daylucky.R;

public class ColorsManager {

    /**
     * theme view line & bg color
     * colorsLineBg[0] setLineColor
     * colorLineBg[1] setBgColor
     */
    public static int[][] colorsLineBg = {
            {Color.parseColor("#00D1CB"), Color.parseColor("#FFFFFF")},
            {Color.parseColor("#DC8692"), Color.parseColor("#EBACB5")},
            {Color.parseColor("#82ABEB"), Color.parseColor("#AFCBF1")},
            {Color.parseColor("#4881A4"), Color.parseColor("#79AAC8")},
            {Color.parseColor("#895774"), Color.parseColor("#99788B")},
            {Color.parseColor("#35CAC7"), Color.parseColor("#71DBD9")},
            {Color.parseColor("#516E95"), Color.parseColor("#789CCE")},
            {Color.parseColor("#F9CC39"), Color.parseColor("#F9DB79")},
            {Color.parseColor("#4E4D86"), Color.parseColor("#7776BC")},
            {Color.parseColor("#4D8471"), Color.parseColor("#6EAE94")},
            {Color.parseColor("#687E46"), Color.parseColor("#96AF6E")},
            {Color.parseColor("#6FCBDE"), Color.parseColor("#98E0EF")},
            {Color.parseColor("#939393"), Color.parseColor("#B6B6B6")},
            {Color.parseColor("#4CCFC1"), Color.parseColor("#8DDED5")},
            {Color.parseColor("#5A70BD"), Color.parseColor("#778BD0")},
            {Color.parseColor("#A099CA"), Color.parseColor("#BFBADA")},
    };

    public static int colorTextView = Color.parseColor("#333333");
    public static int colorTextContent = Color.parseColor("#909094");
    public static int normalBadColor = Color.parseColor("#FF9696");
    public static int normalLuckColor = Color.parseColor("#85E9E6");
    public static int normalWhiteColor = Color.parseColor("#FFFFFF");


    public static final int[] FORTUNE_VIP_TITLE_TEXT_COLOR = {
            Color.parseColor("#FF9696"),
            Color.parseColor("#7F96FE"),
            Color.parseColor("#85ADFF"),
            Color.parseColor("#FFD88F"),
            Color.parseColor("#85E9E6"),
    };

    public static final int[] FORTUNE_VIP_TITLE_LINE_COLOR = {
            Color.parseColor("#FFF5F5"),
            Color.parseColor("#F2F5FF"),
            Color.parseColor("#F3F7FF"),
            Color.parseColor("#FFFAEF"),
            Color.parseColor("#F2FCFC"),
    };
}
