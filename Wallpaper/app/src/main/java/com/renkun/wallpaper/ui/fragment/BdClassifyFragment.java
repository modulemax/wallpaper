package com.renkun.wallpaper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.renkun.wallpaper.R;
import com.renkun.wallpaper.ui.activity.MainActivity;
import com.renkun.wallpaper.ui.adapter.BdClassifyAdapter;


/**
 *
 * 分类页面
 * A simple {@link Fragment} subclass.
 */
public class BdClassifyFragment extends Fragment  {

    private GridView mGridView;
    private BdClassifyAdapter mClassifyAdapter;
    public BdClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_classify, container, false);
        loadData();
        return view;
    }
    public  String title1[]={"小清新","甜素纯","清纯","校花","唯美","气质","嫩萝莉","时尚",
            "长发","可爱","古典美女","素颜","非主流","短发","高雅大气很有范"};
    public  String title[]={"小清新","甜素纯","清纯","校花","气质","嫩萝莉",
            "长发","可爱","古典美女","素颜","非主流","短发"};
    private class GrideItemClickedListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getActivity(), MainActivity.class);
            intent.setPackage(getActivity().getPackageName());
            intent.putExtra("classify", title[position]);
            getActivity().startActivity(intent);
        }
    }

    private void loadData(){

    }
}
