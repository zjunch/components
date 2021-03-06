package com.android.components;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.commonlibrary.router.ZRouterConstants;

@Route(path = ZRouterConstants.MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        findViewById(R.id.tvApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. 跳转并携带参数
                ARouter.getInstance().build(ZRouterConstants.HOME_MAIN_ACTIVITY)
                        .withLong("key1", 666L)
                        .withString("key3", "888")
                        .navigation();
            }
        });
    }
}
