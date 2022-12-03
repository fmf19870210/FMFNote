package cn.jiguang.demo.jshare;

import cn.jiguang.demo.R;
import cn.jiguang.share.facebook.Facebook;
import cn.jiguang.share.facebook.messenger.FbMessenger;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.qqmodel.QZone;
import cn.jiguang.share.twitter.Twitter;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatFavorite;
import cn.jiguang.share.wechat.WechatMoments;
import cn.jiguang.share.weibo.SinaWeibo;
import cn.jiguang.share.weibo.SinaWeiboMessage;

/**
 * Create by wangqingqing
 * On 2020/11/9 17:06
 * Copyright(c) 2020 极光
 * Description
 */
class ShareConstant {

    public static final String[] textArray = {
            "QQ", "微信", "新浪微博",
            "Facebook", "Twitter"
    };

    public static final String[] textAliasArray = {
            QQ.Name, Wechat.Name, SinaWeibo.Name,
            Facebook.Name, Twitter.Name
    };

    public static final int[] iconArray = {
            R.drawable.jshare_demo_share_qq, R.drawable.jshare_demo_share_weixin, R.drawable.jshare_demo_share_sina,
            R.drawable.jshare_demo_share_face, R.drawable.jshare_demo_share_twitter
    };


    public static final String[] plantAliasArray = {
            QQ.Name, QZone.Name, Wechat.Name, WechatMoments.Name,
            WechatFavorite.Name, SinaWeibo.Name, SinaWeiboMessage.Name, Twitter.Name,
            Facebook.Name, FbMessenger.Name
    };

    public static final String[] plantNameArray = {
            "QQ", "QQ空间", "微信", "微信朋友圈",
            "微信收藏", "微博", "微博私信", "Twitter",
            "Facebook", "Messenger"
    };

    public static final int[] plantIconArray = {
            R.drawable.jshare_demo_share_qq, R.drawable.jshare_demo_share_qq_zone, R.drawable.jshare_demo_share_weixin, R.drawable.jshare_demo_share_wc_firend,
            R.drawable.jshare_demo_share_wc_collection, R.drawable.jshare_demo_share_sina, R.drawable.jshare_demo_share_sina, R.drawable.jshare_demo_share_twitter,
            R.drawable.jshare_demo_share_face, R.drawable.jshare_demo_share_messenger
    };

    public static String share_url = "https://www.jiguang.cn";
    public static String share_text = "JShare SDK支持主流社交平台、帮助开发者轻松实现社会化功能！";
    public static String share_title = " 欢迎使用极光社会化组件JShare";
    public static String share_imageurl = "http://img2.3lian.com/2014/f5/63/d/23.jpg";
    public static String share_imageurl_1 = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1308/02/c0/24056523_1375430477597.jpg";
    public static String share_videourl = "http://v.youku.com/v_show/id_XOTQwMDE1ODAw.html?from=s1.8-1-1.2&spm=a2h0k.8191407.0.0";
    public static String share_musicurl = "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3";
    public static String music_shareUrl = "https://y.qq.com/n/yqq/song/109325260_num.html?ADTAG=h5_playsong&no_redirect=1";
}
