package com.isuu.base.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Description NotificationUtils
 * Created by wangisu@qq.com on 2019-09-09
 */
public class NotificationUtils {

    private NotificationUtils() {


        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void create(Context context, int id, int smallIcon, String textTitle, String textContent) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(context.getPackageName(), context.getPackageName(),
                    NotificationManager.IMPORTANCE_DEFAULT);
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //闪光灯
            channel.enableLights(true);
            //锁屏显示通知
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_SECRET);
            //桌面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration(true);
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知取到组
            channel.getGroup();
            //设置可绕过  请勿打扰模式
            channel.setBypassDnd(true);
            //设置震动模式
            channel.setVibrationPattern(new long[]{100, 100, 200});
            //是否会有灯光
            channel.shouldShowLights();
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getPackageName())
                .setSmallIcon(smallIcon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        manager.notify(id, builder.build());
    }

    public static void cancel(@Nullable String tag, final int id) {
        NotificationManagerCompat.from(Utils.getApp()).cancel(tag, id);
    }

    public static void cancel(final int id) {
        NotificationManagerCompat.from(Utils.getApp()).cancel(id);
    }

    public static void cancelAll() {
        NotificationManagerCompat.from(Utils.getApp()).cancelAll();
    }

}
