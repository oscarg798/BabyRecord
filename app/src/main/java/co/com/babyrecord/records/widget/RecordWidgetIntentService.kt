package co.com.babyrecord.records.widget

import android.app.IntentService
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.widget.RemoteViews
import co.com.babyrecord.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by oscarg798 on 1/6/18.
 */
class RecordWidgetIntentService : IntentService(RecordWidgetIntentService::class.java.name) {

    private val mSimpleDateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())


    override fun onHandleIntent(intent: Intent?) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this,
                RecordsWidget::class.java))

        appWidgetIds.forEach {
            val views = RemoteViews(applicationContext.packageName, R.layout.records_widget)
            views.setRemoteAdapter(R.id.mLVWidgetContainer, Intent(applicationContext,
                    WidgetSchedulerService::class.java))
            views.setTextViewText(R.id.mTVTitle, mSimpleDateFormat.format(Calendar.getInstance().time))
            val intent = Intent(applicationContext, RunAnActionOnRecordFromWidgetIntentService::class.java)
            views.setPendingIntentTemplate(R.id.mLVWidgetContainer,
                    PendingIntent.getService(applicationContext, 10, intent, 0))

            appWidgetManager.updateAppWidget(it, views)
        }

    }
}