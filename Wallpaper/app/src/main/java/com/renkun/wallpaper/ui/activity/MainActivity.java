package com.renkun.wallpaper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.data.Api;
import com.renkun.wallpaper.ui.adapter.FragmentAdapter;
import com.renkun.wallpaper.ui.fragment.BdwallpaperFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,View.OnClickListener{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private int CLASSFIY;
    private int mThemeId = -1; // 皮肤主题ID，默认-1 不处理

    private FragmentAdapter mFragmentAdapter;
    public MainActivity() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("theme", -1) != -1) {// 读取皮肤主题ID，-1 不处理
                mThemeId = savedInstanceState.getInt("theme");
                MainActivity.this.setTheme(mThemeId);  //设置主题皮肤
            }
        }
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewPager= (ViewPager) findViewById(R.id.view_pager);
        mTabLayout= (TabLayout) findViewById(R.id.tab_layout);

        swichClassfiy(0);

        findViewById(R.id.iv_bg_red_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_orange_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_yellow_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_green_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_cyan_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_blue_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_red_purple).setOnClickListener(this);
        findViewById(R.id.iv_bg_pink_select).setOnClickListener(this);
        findViewById(R.id.iv_bg_red_brown).setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.openDrawer(GravityCompat.START);
//            View view=findViewById(R.id.theme_select);
//            view.setVisibility(View.VISIBLE);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_0) {
            CLASSFIY=0;
        } else if (id == R.id.nav_1) {
            CLASSFIY=1;
        } else if (id == R.id.nav_2) {
            CLASSFIY=2;
        } else if (id == R.id.nav_manage) {
            CLASSFIY=3;
        } else if (id == R.id.nav_about) {
            showAlertDialog("意见反馈","请联系发送邮件至:1370940829@qq.com");
        } else if (id == R.id.nav_shengming) {
            showAlertDialog("免责声明","美女图片内容来源于网络，我们尊重他人知识产权和其他合法权益。在使用本软件的过程中，如果您认为您的著作权/信息网络传播权被侵犯，请通过QQ和我们取得联系，出具权利通知（保证权利通知并未失实，否则相关法律责任由出具人承担），并详细说明侵权的内容，核实后我们将删除被控内容，断开相关连接");
        } else if (id == R.id.nav_back) {
            finish();
            System.exit(0);
        }
        toolbar.setTitle(Api.tag[CLASSFIY]);
        swichClassfiy(CLASSFIY);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showAlertDialog(String title,String message){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
    }
    private void swichClassfiy(int classfiy){
        toolbar.setTitle(Api.tag[CLASSFIY]);
        //初始化TabLayout的title数据集
        List<Fragment> fragments = new ArrayList<>();
        mTabLayout.removeAllTabs();
        for (String s:Api.tag3[classfiy]){
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
            if (CLASSFIY!=0)fragments.add(new BdwallpaperFragment("壁纸",Api.tag[CLASSFIY],s,0));
            else {fragments.add(new BdwallpaperFragment("壁纸",s,"",0));}
        }
        //创建ViewPager的adapter
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, Api.tag3[classfiy]);
        mViewPager.setAdapter(mFragmentAdapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.iv_bg_red_select:onTheme(R.style.AppColor_red);break;
            case R.id.iv_bg_orange_select:onTheme(R.style.AppColor_orange);break;
            case R.id.iv_bg_yellow_select:onTheme(R.style.AppColor_yellow);break;
            case R.id.iv_bg_green_select:onTheme(R.style.AppColor_green);break;
            case R.id.iv_bg_cyan_select:onTheme(R.style.AppColor_cyan);break;
            case R.id.iv_bg_blue_select:onTheme(R.style.AppColor_blue);break;
            case R.id.iv_bg_red_purple:onTheme(R.style.AppColor_purple);break;
            case R.id.iv_bg_pink_select:onTheme(R.style.AppColor_pink);break;
            case R.id.iv_bg_red_brown:onTheme(R.style.AppColor_brown);break;
            default:break;
        }
    }

    /**
     * viewpager .切换动画
     */
    class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private boolean mIsExit;
    private void exit(){
        if (mIsExit){
            finish();
            System.exit(0);
        }
        else {
            mIsExit=true;
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //execute the task
                    mIsExit = false;
                }
            }, 2000);
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    // 设置主题，并重建
    private void onTheme(int iThemeId){
        mThemeId = iThemeId;
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        intent.setPackage(getPackageName());
        this.recreate();
    }
    // 保存主题ID，onCreate 时读取主题
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", mThemeId);
    }
}
