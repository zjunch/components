package com.android.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.commonlibrary.router.ZRouterConstants;

@Route(path = ZRouterConstants.MINE_MAIN_ACTIVITY)
public class MineMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_mine_main);
        findViewById(R.id.tvMine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. 跳转并携带参数
                ARouter.getInstance().build(ZRouterConstants.MAIN_ACTIVITY)
                        .withLong("key1", 666L)
                        .withString("key3", "888")
                        .navigation();
            }
        });
    }
}
