package com.renkun.wallpaper.data;

import java.util.ArrayList;

/**
 * Created by rk on 2015/11/9.
 */
public final class Bdimg {
    public String col;
    public String tag;
    public String tag3;
    public int totalNum;
    public int start_index;
    public int return_number;
    public ArrayList<DATA> imgs;
    //图片信息
    public static class DATA {
        public long id;
        public String desc;
        public String downloadUrl;//原图
//        public int imageWidth;
//        public int imageHeight;

        public String thumbnailUrl;//小图
//        public int thumbnailWidth;
//        public int thumbnailHeight;

        public String thumbLargeUrl;//大图
//        public int thumbLargeTnWidth;
//        public int thumbLargeTnHeight;

    }
}
