package com.epam.itweek.commonsensesecurity.downloader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RegularDataActivity extends Activity {

    private static final String URL_PRODUCTION_BACKEND = "http://google.com";

    @InjectView(R.id.update) Button mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.update) void onUpdateDataClick() {
        UpdateDataIntentService.startDataUpdate(this, URL_PRODUCTION_BACKEND);
    }

}
