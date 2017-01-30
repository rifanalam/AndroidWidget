package com.shohoz.launcher;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            String number = String.format("%03d", (new Random().nextInt(900) + 100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.activity_main);

            PackageManager pm = context.getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage("com.shohoz.launch.consumer");
            if(launchIntent==null){
                launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.shohoz.launch.consumer"));
            }else{
                launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, launchIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.imageViewLaunch, pendingIntent);

            Intent busIntent = pm.getLaunchIntentForPackage("com.shohoz.bus.android");
            if(busIntent==null){
                busIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.shohoz.bus.android"));
            }else{
                busIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            }
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context,
                    0, busIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.imageViewBus, pendingIntent2);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}