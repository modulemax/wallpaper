package com.renkun.wallpaper.ui.activity;

/**
 * Created by rk on 2015/10/22.
 */

import a.b.c.onlineconfig.pcbjsc;
import a.b.c.pcafsc;
import a.b.c.st.pcbrsc;
import a.b.c.st.pcbssc;
import a.b.c.st.pcbtsc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.data.Config;

public class WelcomeActivity extends Activity {
    pcbrsc splashView;
    Context context;
    View splash;
    RelativeLayout splashLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        pcafsc.getInstance(context).pmexsm(false);
        pcafsc.getInstance(context).init("7eb8c4731aa87b4d", "e8e1c79142b53be2",false);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setPackage(getPackageName());
        Config.isShowAD=getSharedPreferences("wallpaper", Context.MODE_PRIVATE).getBoolean("isShowAD",false);
        isShowAd();
        if (!Config.isShowAD){
            startActivity(intent);
            finish();
        }
        else {
            // 第二个参数传入目标activity，或者传入null，改为setIntent传入跳转的intent
            splashView = new pcbrsc(context, null);
            // 设置是否显示倒数
            splashView.setShowReciprocal(true);
            // 隐藏关闭按钮
            splashView.hideCloseBtn(true);
            splashView.setIntent(intent);
            splashView.setIsJumpTargetWhenFail(true);
            splash = splashView.getSplashView();
            setContentView(R.layout.activity_welcom);
            splashLayout = ((RelativeLayout) findViewById(R.id.splashview));
            splashLayout.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
            params.addRule(RelativeLayout.ABOVE, R.id.cutline);
            splashLayout.addView(splash, params);
            pcbtsc.pmahsm(context)
                    .pmbdsm(context, splashView, new pcbssc() {
                        @Override
                        public void pmapsm() {
                            splashLayout.setVisibility(View.VISIBLE);
                            splashLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pic_enter_anim_alpha));
                        }
                        @Override
                        public void pmaosm() {

                        }
                        @Override
                        public void pmarsm() {

                        }
                        @Override
                        public void pmaqsm(boolean isWebPath) {
                        }
                    });
        }
    }
    private void isShowAd(){
        pcafsc.getInstance(WelcomeActivity.this).pmbism("isShowAD", new pcbjsc() {
            @Override
            public void pmeasm(String key, String value) {
                if ("0".equals(value)) Config.isShowAD = false;
                if ("1".equals(value)) Config.isShowAD = true;
                getSharedPreferences("wallpaper", Context.MODE_PRIVATE).edit().putBoolean("isShowAD", Config.isShowAD).commit();
            }
            @Override
            public void pmdzsm(String key) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        // 取消后退键
    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port
        }
    }

}