package com.renkun.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.data.Api;
import com.renkun.wallpaper.data.Bdimg;
import com.renkun.wallpaper.data.OkHttpClientManager;
import com.renkun.wallpaper.ui.adapter.BdImgRecyclerViewAdapter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rk on 2015/11/9.
 */
public class BdwallpaperFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener{
    //百度图片URL参数
    private int pn=0;
    private int rn=14;
    private String col="壁纸";
    private String tag="风景";
    private String tag3="全部";
    private int width=0;
    private int height=0;
    private int ic=0;
    private String ie="utf8";
    private String oe="utf-8";
    private String fr="channel";
    private String p="channel";
    private int from=1;
    private String t= "";

    public RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BdImgRecyclerViewAdapter mBdImgRecyclerViewAdapter;

    private boolean freshFlag;
    public ArrayList<Bdimg.DATA> data = new ArrayList<>();

    public BdwallpaperFragment(String col, String tag, String tag3, int ic) {
        this.col = col;
        this.tag = tag;
        this.tag3 = tag3;
        this.ic = ic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bd_feed, container, false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyler_view);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initView(view);
        loadFirst();
        return view;
    }

    private void initView(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBdImgRecyclerViewAdapter=new BdImgRecyclerViewAdapter(getActivity(),this);
        mRecyclerView.setAdapter(mBdImgRecyclerViewAdapter);
    }
    private void loadFirst() {
        pn = 0;
        String url = String.format(Api.BD_HTTP, pn*rn, rn, col ,tag, tag3,width,height,ic,
                ie,oe,fr,p,t);
        freshFlag = true;
        freshFlag = false;
        loadData(url);
    }

    public void loadnext() {
        pn ++;
        String url = String.format(Api.BD_HTTP, pn*rn, rn, col ,tag, tag3,width,height,ic,
                ie,oe,fr,p,t);
        freshFlag = true;
        loadData(url);
    }
    private void loadData(String url) {
        Log.d("bb",url);
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();
        OkHttpClientManager.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "网络有点问题", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //解析字符串
                String s = response.body().string();
                Log.d("bb", s);
                Bdimg bDpic = OkHttpClientManager
                        .getJsonBean(s, Bdimg.class);
                if (!CheckData(bDpic))return;
                if (freshFlag) {
                    data.addAll(bDpic.imgs);
                } else data = bDpic.imgs;
                if (getActivity()!=null)getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBdImgRecyclerViewAdapter.data = data;
                        mBdImgRecyclerViewAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    @Override
    public void onRefresh() {
        loadFirst();
    }
    public boolean CheckData(Bdimg bDpic) {
        if (bDpic == null || bDpic.imgs.size() < 1) return false;
        for (Bdimg.DATA data : bDpic.imgs) {
            if (data.id == 0) bDpic.imgs.remove(data);
        }
        return true;
    }
}
