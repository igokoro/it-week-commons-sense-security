package com.epam.itweek.commonsensesecurity.downloader;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import hugo.weaving.DebugLog;

public class UpdateDataIntentService extends IntentService {
    private static final String TAG = UpdateDataIntentService.class.getSimpleName();

    private static final String ACTION_UPDATE_DATA = "com.epam.itweek.commonsensesecurity.action.UPDATE_DATA";
    private static final String EXTRA_URL = "com.epam.itweek.commonsensesecurity.extra.URL";

    private static final String TOKEN = "token";

    public static void startDataUpdate(Context context, String url) {
        Intent intent = new Intent(context, UpdateDataIntentService.class);
        intent.setAction(ACTION_UPDATE_DATA);
        intent.putExtra(EXTRA_URL, url);
        context.startService(intent);
    }

    public UpdateDataIntentService() {
        super("UpdateDataIntentService");
    }

    @Override @DebugLog
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_DATA.equals(action)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                performDataUpdate(url);
            }
        }
    }

    private void performDataUpdate(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader(TOKEN, AuthManager.getInstance().getToken())
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String newData = response.body().string();
            Log.w(TAG, "performDataUpdate: backend: [" + url + "]");
            Log.w(TAG, "performDataUpdate: token sent to backend: [" + request.header(TOKEN) + "]");
            storeDataInDb(newData);
        } catch (IOException e) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "performDataUpdate: failed to update data", e);
            }
        }
    }

    private void storeDataInDb(String newData) {
        // real db code goes here
    }

}
