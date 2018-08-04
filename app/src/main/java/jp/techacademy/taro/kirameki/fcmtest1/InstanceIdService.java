package jp.techacademy.taro.kirameki.fcmtest1;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {
    private final static String TAG = "FirebaseCloudMessageing";

    @Override
    public void onTokenRefresh() {
        // Firebase側から任意のタイミングで更新される
        // 本来であれば、FirebaseのrefreshedTokenをサーバなどに登録しておいて
        // メッセージ送信時に送信先の指定として利用する
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, refreshedToken);
    }
}