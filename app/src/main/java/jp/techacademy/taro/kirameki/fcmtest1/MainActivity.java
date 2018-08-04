package jp.techacademy.taro.kirameki.fcmtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "FirebaseCloudMessageing";

    /*
    実行手順
    アプリを実行して、表示されるトークンをメモする
    Firebaseコンソールで、メッセージとともにトークンを指定する
    Firebaseから通知が送信されると、アプリで通知を表示する（MessagingServiceクラス）
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 本来であれば、FirebaseのrefreshedTokenをサーバなどに登録しておいて
        // メッセージ送信時に送信先の指定として利用する
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        EditText editText = findViewById(R.id.editText);
        editText.setText(refreshedToken);
        Log.d(TAG, refreshedToken);

        //Push通知の購読開始(トピックを指定する場合に必要)
        // FirebaseMessaging.getInstance().subscribeToTopic("mytopic");
        //購読解除(トピックを指定する場合に必要)
        //FirebaseMessaging.getInstance().unsubscribeFromTopic("mytopic");
    }
}
