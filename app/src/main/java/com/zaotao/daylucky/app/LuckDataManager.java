package com.zaotao.daylucky.app;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zaotao.base.utils.NetworkUtils;
import com.zaotao.base.utils.SPUtils;
import com.zaotao.daylucky.module.entity.ThemeEntity;

public class LuckDataManager {

    private static volatile LuckDataManager instance;

    public static LuckDataManager getInstance() {
        if (instance == null) {
            synchronized (LuckDataManager.class) {
                if (instance == null) {
                    instance = new LuckDataManager();
                }
            }
        }
        return instance;
    }

    public int getSelectConstellationIndex() {
        return SPUtils.getInstance().getInt("zao_tao_local_select_data", 0);
    }

    public void setSelectConstellationIndex(int var) {
        SPUtils.getInstance().put("zao_tao_local_select_data", var);
    }

    public void saveThemeData(ThemeEntity themeEntity) {
        SPUtils.getInstance().put("zao_tao_local_select_theme_data", new Gson().toJson(themeEntity));
    }

    public ThemeEntity getThemeData() {
        String themeJson = SPUtils.getInstance().getString("zao_tao_local_select_theme_data");
        return new Gson().fromJson(themeJson, ThemeEntity.class);
    }

    public boolean isEmptyLocalTheme() {
        return TextUtils.isEmpty(SPUtils.getInstance().getString("zao_tao_local_select_theme_data"));
    }

    public int getImageRes() {
        return Constants.CONSTELLATION_IMG[NetworkUtils.isConnected() ? getSelectConstellationIndex() : 0];
    }

    public void setVipMobile(String mobile){
        SPUtils.getInstance().put("zao_tao_local_mobile_data", mobile);
    }
    public String getVipMobile(){
        return SPUtils.getInstance().getString("zao_tao_local_mobile_data", "");
    }

}
