package com.zaotao.daylucky.widget.launcher;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.RemoteViews;

import com.zaotao.base.utils.ScreenUtils;
import com.zaotao.daylucky.App;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.ColorsManager;
import com.zaotao.daylucky.app.AppDataManager;
import com.zaotao.daylucky.module.entity.ThemeEntity;

public class UpdateAppWidget {

    private static volatile UpdateAppWidget instance;

    public static UpdateAppWidget getInstance() {
        if (instance == null) {
            synchronized (UpdateAppWidget.class) {
                if (instance == null) {
                    instance = new UpdateAppWidget();
                }
            }
        }
        return instance;
    }

    public void updateAppWidget() {
        ComponentName componentName = new ComponentName(App.getApplication(), DayLuckyAppWidget.class);
        AppWidgetManager awm = AppWidgetManager.getInstance(App.getApplication());
        awm.updateAppWidget(componentName, getRemoteViews(App.getApplication().getPackageName()));
    }

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context.getPackageName()));
    }

    private RemoteViews getRemoteViews(String packageName){
        ThemeEntity themeEntity = AppDataManager.getInstance().getThemeData();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(packageName, R.layout.day_lucky_app_widget);
        views.setTextViewText(R.id.item_theme_style_day_text, themeEntity.getDay());
        views.setTextViewText(R.id.item_theme_style_month_text, themeEntity.getMonth());
        views.setTextViewText(R.id.item_theme_style_week_text, themeEntity.getWeek());
        views.setTextViewText(R.id.item_theme_style_content_text, themeEntity.getText());
        views.setTextViewText(R.id.item_theme_style_day_lucky_text, themeEntity.getLucky());
        views.setTextViewText(R.id.item_theme_style_day_bad_text, themeEntity.getBad());

        views.setTextColor(R.id.item_theme_style_day_text, themeEntity.getDayColor());
        views.setTextColor(R.id.item_theme_style_week_text, themeEntity.getWeekColor());
        views.setTextColor(R.id.item_theme_style_month_text, themeEntity.getMonthColor());
        views.setTextColor(R.id.item_theme_style_day_lucky_text, themeEntity.getLuckyColor());
        views.setTextColor(R.id.item_theme_style_day_bad_text, themeEntity.getBadColor());
        views.setTextColor(R.id.item_theme_style_content_text, themeEntity.getTextColor());

        if (themeEntity.getBgColor() == ColorsManager.normalWhiteColor) {
            views.setTextColor(R.id.item_theme_style_day_lucky, ColorsManager.colorTextView);
            views.setTextColor(R.id.item_theme_style_day_bad, ColorsManager.colorTextView);
        } else {
            views.setTextColor(R.id.item_theme_style_day_lucky, ColorsManager.normalWhiteColor);
            views.setTextColor(R.id.item_theme_style_day_bad, ColorsManager.normalWhiteColor);
        }

        Bitmap bitmap0 = Bitmap.createBitmap(6, 80, Bitmap.Config.ARGB_8888);
        bitmap0.eraseColor(themeEntity.getLineColor());
        views.setImageViewBitmap(R.id.item_theme_style_line, bitmap0);
        Bitmap bitmap = getBitmap(ScreenUtils.getScreenWidth(), 450, themeEntity.getBgColor());
        views.setImageViewBitmap(R.id.item_theme_style_bg, bitmap);

        return views;
    }

    private static Bitmap getBitmap(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
//        bitmap.eraseColor(color);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, width, height), 20, 20, paint);

        return bitmap;
    }

}
