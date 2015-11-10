package com.renkun.wallpaper.data;

/**
 * Created by rk on 2015/11/9.
 *
 pn	0
 rn	24
 col	壁纸
 tag	建筑
 tag3	美轮美奂
 width	1366
 height	768
 ic	0 主题
 ie	utf8
 oe	utf-8
 image_id
 fr	channel
 p	channel
 from	1
 app	img.browse.channel.wallpaper
 t	0.1763717900030315
 */
public final class Api {
    public static final String BD_HTTP=
            "http://image.baidu.com/data/imgs?pn=%1$d&rn=%2$d&col=%3$s&tag=%4$s&tag3=%5$s&width=%6$d&height=%7$d&ic=%8$d&ie=%9$s&oe=%10$s&image_id=&fr=%11$s&p=%12$s&from=1&app=img.browse.channel.wallpaper&t=%13$s";

 public static final String[] tag=
         {"热门推荐","风景","美女","动漫","创意","名车","影视","游戏"};
 public static final String[][] tag3={
         {"编辑推荐","护眼","国家地理","小鲜肉","萌宠"},
         {"全部","花草植物","国外风光","唯美意境","旅游风光","海底世界","冰天雪地","山水相映","海滩沙滩","在路上","自然风光","沙漠戈壁","璀璨星空"},
         {"全部","可爱","写真","日韩写真","清新","校花","森系女孩"},
         {"全部","阿狸","火影忍者","海贼王","名侦探柯南","守护甜心","卡通"},
         {},
         {},
         {},
         {},
         {}};
}
