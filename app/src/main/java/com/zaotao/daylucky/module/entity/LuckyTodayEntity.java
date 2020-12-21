package com.zaotao.daylucky.module.entity;

import java.io.Serializable;
/**
 *  {
 *                 "y": 78,
 *                 "x": "今天"
 *             },
 *             {
 *                 "y": 89,
 *                 "x": "12.22"
 *             },
 *
 *             . . .
 *   }
 */
public class LuckyTodayEntity implements Serializable {


    private String x;
    private int y;

    public LuckyTodayEntity(String x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}
