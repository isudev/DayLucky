package com.zaotao.daylucky.app;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zaotao.base.utils.SPUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.entity.ThemeEntity;

public class LocalDataManager {
    private static volatile LocalDataManager instance;

    public static LocalDataManager getInstance() {
        if (instance == null) {
            synchronized (LocalDataManager.class) {
                if (instance == null) {
                    instance = new LocalDataManager();
                }
            }
        }
        return instance;
    }

    public int getSelectLocalData() {
        return SPUtils.getInstance().getInt("zao_tao_local_select_data",0);
    }

    public void setTipsFromHomeGuidePage(int var) {
        SPUtils.getInstance().put("zao_tao_local_select_data", var);
    }

    public void saveThemeData(ThemeEntity themeEntity) {
        SPUtils.getInstance().put("zao_tao_local_select_theme_data", new Gson().toJson(themeEntity));
    }

    public ThemeEntity getThemeData() {
        String themeJson = SPUtils.getInstance().getString("zao_tao_local_select_theme_data");
        ThemeEntity themeEntity = new Gson().fromJson(themeJson, ThemeEntity.class);
        return themeEntity;
    }

    public boolean isEmptyLocalTheme(){
        return TextUtils.isEmpty(SPUtils.getInstance().getString("zao_tao_local_select_theme_data"));
    }

    public int getImageRes() {
        return IMG[getSelectLocalData()];
    }

    public static final int[] IMG = {
            R.drawable.ic_item_setting_select_view0,
            R.drawable.ic_item_setting_select_view1,
            R.drawable.ic_item_setting_select_view2,
            R.drawable.ic_item_setting_select_view3,
            R.drawable.ic_item_setting_select_view4,
            R.drawable.ic_item_setting_select_view5,
            R.drawable.ic_item_setting_select_view6,
            R.drawable.ic_item_setting_select_view7,
            R.drawable.ic_item_setting_select_view8,
            R.drawable.ic_item_setting_select_view9,
            R.drawable.ic_item_setting_select_view10,
            R.drawable.ic_item_setting_select_view11
    };
}
