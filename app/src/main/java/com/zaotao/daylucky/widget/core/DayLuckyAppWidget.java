package com.zaotao.daylucky.widget.core;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.zaotao.base.utils.ImageUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.LocalDataManager;
import com.zaotao.daylucky.module.entity.ThemeEntity;

/**
 * Implementation of App Widget functionality.
 */
public class DayLuckyAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        ThemeEntity themeEntity = LocalDataManager.getInstance().getThemeData();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.day_lucky_app_widget);
        views.setTextViewText(R.id.item_theme_style_day_text, themeEntity.getDay());
        views.setTextViewText(R.id.item_theme_style_month_text, themeEntity.getMonth());
        views.setTextViewText(R.id.item_theme_style_week_text, themeEntity.getWeek());
        views.setTextViewText(R.id.item_theme_style_content_text, themeEntity.getText());
        views.setTextViewText(R.id.item_theme_style_day_lucky_text, themeEntity.getLucky());
        views.setTextViewText(R.id.item_theme_style_day_bad_text, themeEntity.getBad());

        views.setTextColor(R.id.item_theme_style_day_text,themeEntity.getDayColor());
        views.setTextColor(R.id.item_theme_style_week_text,themeEntity.getWeekColor());
        views.setTextColor(R.id.item_theme_style_month_text,themeEntity.getMonthColor());
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

