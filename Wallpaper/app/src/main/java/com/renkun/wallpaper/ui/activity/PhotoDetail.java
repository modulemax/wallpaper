package com.renkun.wallpaper.ui.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.util.WallpaperUtli;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

public class PhotoDetail extends AppCompatActivity {
    private String desc;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        Intent intent = getIntent();
        desc = intent.getStringExtra("desc");
        url = intent.getStringExtra("url");
        getSupportActionBar().setTitle(desc);
        ImageView photoView = (ImageView) findViewById(R.id.iv_photo);
        Picasso.with(this)
                .load(url)
                .config(Bitmap.Config.ARGB_8888)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(photoView);
        TextView save= (TextView) findViewById(R.id.save);
        TextView wallpaper= (TextView) findViewById(R.id.wallpaper);
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "设置为壁纸吗？", Snackbar.LENGTH_LONG)
                        .setAction("是的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WallpaperUtli.setBitmap(PhotoDetail.this,url);
                                Snackbar.make(v, "正在设置。。", Snackbar.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "要保存图片吗？", Snackbar.LENGTH_LONG)
                        .setAction("是的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WallpaperUtli.savePic(PhotoDetail.this,
                                        url, desc+ ".png");
                                Snackbar.make(v, "正在保存。。", Snackbar.LENGTH_LONG).show();

                            }
                        }).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_photo_detail, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
