package com.bwf.landz.ui.welcome;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.bwf.framwork.base.BaseActivity;
import com.bwf.framwork.share.SharePrefreceHelper;
import com.bwf.framwork.utils.IntentUtils;
import com.bwf.landz.R;
import com.bwf.landz.ui.MainActivity;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity implements Handler.Callback {

    private Handler mHandle;

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mHandle = new Handler(this);
        mHandle.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                if (SharePrefreceHelper.getInstence(WelcomeActivity.this).isFirst())
                    IntentUtils.openActivity(this, GuidActivity.class);
                else
                    IntentUtils.openActivity(this, MainActivity.class);

                finish();
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mHandle.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
