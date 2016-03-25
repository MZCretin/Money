package com.diandian.ycdyus.moneymanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.diandian.ycdyus.moneymanager.R;
import com.diandian.ycdyus.moneymanager.utils.Utils;

public class SpalshActivity extends BaseActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //代表第一次进入
                case 0:
                    Intent intent = new Intent(SpalshActivity.this,GuideActivity.class);
                    startActivity(intent);
                    SpalshActivity.this.finish();
                    break;
                //代表不是第一次进入
                case 1:
                    Intent inten1 = new Intent(SpalshActivity.this,MainActivity_.class);
                    startActivity(inten1);
                    SpalshActivity.this.finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        getSupportActionBar().hide();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utils.isFirst(SpalshActivity.this)) {
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    handler.sendMessage(msg);
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }, 1 * 2000);
    }
}
