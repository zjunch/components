package com.android.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.commonlibrary.router.ZRouterConstants;

@Route(path = ZRouterConstants.FIND_MAIN_ACTIVITY)
public class FriendMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_main);
        ARouter.getInstance().inject(this);
        findViewById(R.id.tvFriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. 跳转并携带参数
                ARouter.getInstance().build(ZRouterConstants.DISCOVER_MAIN_ACTIVITY)
                        .withLong("key1", 666L)
                        .withString("key3", "888")
                        .navigation();
            }
        });
    }
}
