package com.lrchao.fakejobsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lrchao.fakejob.JobApp;
import com.lrchao.fakejob.utils.NavUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JobApp.getInstance().init(this);
        JobApp.getInstance().setUrlHost("http://api.shuboman.cn/");


        findViewById(R.id.btn_nav_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.get().navToMainActivity(getApplicationContext());
            }
        });
    }
}
