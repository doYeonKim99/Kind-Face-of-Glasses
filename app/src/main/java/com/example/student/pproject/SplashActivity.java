package com.example.student.pproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.net.LinkAddress;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Student on 2016-06-19.
 */
public class SplashActivity extends Activity{

    int splashScreen;
    LinearLayout splashLayout;
    Handler mHandler;
    boolean isClick;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // xml 소스 참조
        splashLayout = (LinearLayout)findViewById(R.id.splashLayout);

        // 처음화면 0
        splashScreen = 0;
        // 클릭 이벤트가 있었는지 확인
        isClick = true;

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (splashScreen) {
                    case 0:
                        // 두번째 화면
                        splashScreen = 1;

                        splashLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.splash));
                        mHandler.sendEmptyMessage(splashScreen);
                        break;
                    case 1:
                        splashScreen = 2;
                        mHandler.sendEmptyMessageDelayed(splashScreen, 2000);
                        break;
                    case 2:
                        // 액티비티 종료
                        SplashActivity.this.finish();
                        break;
                    case 3:
                        // 딜레이이벤트 클릭이 없을 경우 바로 0이벤트로 보낸다
                        if (isClick && splashScreen == 0) {
                            splashScreen = 0;
                            mHandler.sendEmptyMessage(splashScreen);
                        }
                        break;
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    public void splashOnclick(View v){
        switch (splashScreen) {
            case 0:
                splashScreen = 0;

                isClick = false;
                mHandler.sendEmptyMessage(splashScreen);
                break;

            case 1:
                splashScreen = 2;
                mHandler.sendEmptyMessage(splashScreen);
                break;
        }
    }
}
