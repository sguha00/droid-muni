package info.yasskin.droidmuni;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class PredictionWidgetProvider extends AppWidgetProvider{
  public static String CLICK_ACTION = "com.example.android.weatherlistwidget.CLICK";
  public static String REFRESH_ACTION = "com.example.android.weatherlistwidget.REFRESH";
  public static String EXTRA_CITY_ID = "com.example.android.weatherlistwidget.city";

//  private static HandlerThread sWorkerThread;
//  private static Handler sWorkerQueue;
//  private static WeatherDataProviderObserver sDataObserver;

  public PredictionWidgetProvider() {
      // Start the worker thread
//      sWorkerThread = new HandlerThread("WeatherWidgetProvider-worker");
//      sWorkerThread.start();
//      sWorkerQueue = new Handler(sWorkerThread.getLooper());
  }
  
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//    // BroadcastReceivers have a limited amount of time to do work, so for this sample, we
//    // are triggering an update of the data on another thread.  In practice, this update
//    // can be triggered from a background service, or perhaps as a result of user actions
//    // inside the main application.
//    final Context context = ctx;
//    sWorkerQueue.removeMessages(0);
//  
//    sWorkerQueue.post(new Runnable() {
////      @Override
//      public void run() {
//          final AppWidgetManager mgr = AppWidgetManager.getInstance(co«ntext);
//          final ComponentName cn = new ComponentName(context, PredictionWidgetProvider.class);
//          mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.predictionTime);
//      }
//  });
    
    ComponentName thisWidget = new ComponentName(context,
        PredictionWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int widgetId : allWidgetIds) {
      // Create some random data
      int number = (new Random().nextInt(100));

      RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_view);
      Log.w("WidgetExample", String.valueOf(number));
      // Set the text
      remoteViews.setTextViewText(R.id.predictionTime, String.valueOf(number));
      
//       Register an onClickListener
      Intent intent = new Intent(context, PredictionWidgetProvider.class);

      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

      PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
          0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      remoteViews.setOnClickPendingIntent(R.id.container, pendingIntent);
      appWidgetManager.updateAppWidget(widgetId, remoteViews);
    }
    
    
  }
  
  @Override
  public void onDeleted(Context context, int[] appWidgetIds) {

  }
  
  

}
