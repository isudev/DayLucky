package com.zaotao.daylucky.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

import com.zaotao.daylucky.R;

/**
 * Description AppUtils
 * Created by wangisu@qq.com on 12/30/20.
 */
public class AppUtils {

    public static void copyClipboard(Context context, String content) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText(context.getString(R.string.app_name), content);
        cm.setPrimaryClip(mClipData);
    }

    public static String getDeviceBrand() {
        String brand = Build.BRAND.toLowerCase();
        brand = brand.equals("unknown") ? Build.MANUFACTURER.toLowerCase() : brand;
        return brand;
    }

}
