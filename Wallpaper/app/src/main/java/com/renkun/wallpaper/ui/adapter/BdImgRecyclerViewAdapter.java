package com.renkun.wallpaper.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.data.Bdimg;
import com.renkun.wallpaper.ui.activity.PhotoDetail;
import com.renkun.wallpaper.ui.fragment.BdwallpaperFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Author       : yanbo
 * Date         : 2015-06-02
 * Time         : 09:47
 * Description  :
 */
public class BdImgRecyclerViewAdapter extends RecyclerView.Adapter<BdImgRecyclerViewAdapter.ViewHolder> {
    private int[] colors = {R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
            R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
            R.color.color_8, R.color.color_9,};

    public final Context mContext;
    public ArrayList<Bdimg.DATA> data;
    private BdwallpaperFragment mBdwallpaperFragment;
    public BdImgRecyclerViewAdapter(Context mContext,BdwallpaperFragment fragment) {
        this.mContext = mContext;
        mBdwallpaperFragment=fragment;
    }

    @Override
    public BdImgRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bd_feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BdImgRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mImageView.setBackgroundColor(mContext.getResources().getColor(colors[position % (colors.length)]));
        Picasso.with(mContext)
                .load(getUrl(data.get(position)))
                .centerInside()
                .resize(720, 1280)
                .into(holder.mImageView);
        if (position==data.size()-2){mBdwallpaperFragment.loadnext();}
    }
    @Override
    public int getItemCount() {
        if (data!=null&&data.size()>1)return data.size();
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mImageView= (ImageView) view.findViewById(R.id.pic);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, PhotoDetail.class);
                    intent.setPackage(mContext.getPackageName());
                    intent.putExtra("url",data.get(getPosition()).downloadUrl);
                    intent.putExtra("desc",data.get(getPosition()).desc);
                    mContext.startActivity(intent);
                }
            });
        }
    }
    //优先返回小图的url
    private String getUrl(Bdimg.DATA data){
        String url=data.downloadUrl;
        if (url.startsWith("http://imgt"))
            url=data.thumbLargeUrl;
        if (url.startsWith("http://imgt"))
            url=data.downloadUrl;
        return url;
    }
}
