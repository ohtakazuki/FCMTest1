package jp.techacademy.taro.kirameki.fcmtest1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MessagingService extends FirebaseMessagingService {
    //private final static String TAG = MessagingService.class.getSimpleName();
    private final static String TAG = "FirebaseCloudMessageing";

    @Override
    public void onMessageReceived(RemoteMessage message){
        // intentからデータを取得して使う
        final String body = message.getNotification().getBody();
        final String title = message.getNotification().getTitle();
        // 詳細オプションの値を取得
        final String key1 = message.getData().get("key1");

        Log.d(TAG, "body=" + body);
        Log.d(TAG, "title=" + title);
        Log.d(TAG, "key1=" + key1);

        sendNotification(title, body, key1);
    }

    // onMessageReceived はアプリがバックグラウンド時には呼ばれない
    // handleIntentを使う
//    @Override
//    public void handleIntent(Intent intent) {
//        // intentからデータを取得して使う
//        final String body = intent.getStringExtra("gcm.notification.body");
//        final String title = intent.getStringExtra("gcm.notification.title");
//        // 詳細オプションの値を取得
//        final String key1 = intent.getStringExtra("key1");
//
//        Log.d("FCM", "body=" + body);
//        Log.d("FCM", "title=" + title);
//        Log.d("FCM", "key1=" + key1);
//
//        sendNotification(title, body, key1);
//    }

    // 通知を表示
    private void sendNotification(String title, String body, String key1) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setSubText(key1)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }
}