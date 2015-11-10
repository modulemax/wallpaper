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
        // 初始化接口，应用启动的时候调用
        // 参数：appId, appSecret, 调试模式
        Intent intent = new Intent(this, MainActivity.class);
        intent.setPackage(getPackageName());
        //App.isShowAD=getSharedPreferences("mnpic", Context.MODE_PRIVATE).getBoolean("isShowAD",false);
        if (false){
            startActivity(intent);
            finish();
        }
        else {
            pcafsc.getInstance(context).init("7eb8c4731aa87b4d", "e8e1c79142b53be2",false);
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
                            Log.d("youmisdk", "展示成功");
                        }
                        @Override
                        public void pmaosm() {
                            Log.d("youmisdk", "展示失败");
                        }
                        @Override
                        public void pmarsm() {
                            Log.d("youmisdk", "展示关闭");
                        }
                        @Override
                        public void pmaqsm(boolean isWebPath) {
                            Log.i("YoumiAdDemo", "插屏点击");
                        }
                    });
        }
        //isShowAd();
    }
    //shi fouxianshi ad
    private void isShowAd(){
        pcafsc.getInstance(WelcomeActivity.this).pmbism("isShowAD", new pcbjsc() {
            @Override
            public void pmeasm(String key, String value) {
                // TODO Auto-generated method stub
                // 获取在线参数成功
                //if ("0".equals(value)) App.isShowAD = false;
                //if ("1".equals(value)) App.isShowAD = true;
                //getSharedPreferences("mnpic", Context.MODE_PRIVATE).edit().putBoolean("isShowAD", App.isShowAD).commit();
            }

            @Override
            public void pmdzsm(String key) {
                // TODO Auto-generated method stub
                // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
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