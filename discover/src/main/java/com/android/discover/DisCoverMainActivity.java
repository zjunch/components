package com.android.discover;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.commonlibrary.router.ZRouterConstants;

@Route(path = ZRouterConstants.DISCOVER_MAIN_ACTIVITY)
public class DisCoverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_cover_main);
        ARouter.getInstance().inject(this);
        findViewById(R.id.tvDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. 跳转并携带参数
                ARouter.getInstance().build(ZRouterConstants.MINE_MAIN_ACTIVITY)
                        .withLong("key1", 666L)
                        .withString("key3", "888")
                        .navigation();
            }
        });
    }
}
