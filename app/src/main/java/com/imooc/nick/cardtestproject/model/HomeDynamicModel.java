package com.imooc.nick.cardtestproject.model;

import com.imooc.nick.cardtestproject.Util.StringUtils;
import com.imooc.nick.cardtestproject.adapter.DynamicImageApdater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class HomeDynamicModel implements Serializable {
    private static final long serialVersionUID = 165165416L;
    private static final String TAG = "HomeDynamicModel";
    public String uuid = "";
    public int id; //未发送成功 id=0
    public int type = 1;
    public boolean isSendFail;
    public int originalId;
    public int userId;
    public String screenName;      //发布者昵称
    public String createTime; //创建时间
    public String updateTime; ////最后评论时间，无评论则没有此字段
    public int sort;
    public boolean isAllowOperate;//是否允许操作回复
    public int isvip = 0; //是否是认证用户0:未认证  1:认证
    public int user_type;//0：普通帐号 1：美柚号，2：品牌号
    public TopicAvatarModel avatarModel; //用户头像

    public transient DynamicImageApdater imagesListAdapter;//图片列表adapter

    public int praiseNum = 0; //赞的数量
    public int isPraise = 0; //是否已经点赞1为点赞  0  未点赞
    public int commentNum;  //评论数量

    public String title;
    public String content; //内容

    public String shareWords;//分享感言 TOPIC_SHARE_TYPE
    public String publisher; //原帖子发布者

    //type = 3 圈子推荐
    public String iconUrl;

    public String typeIcon;
    public String avatUrl;//推广的icon，优先级最高
    public String circleName;
    public int forumId;

    public String idUrl; //web页面是url地址，其它为id
    //贴士
    public String tipUrl;//分享的贴士url
    public String tipCategory; //分类标题
    public int tipCategoryId;//分类id
    public int tipId; //贴士id
    public int topicId; //贴士转话题后的id

    public int isMoreComment = 0; //是否有更多评论
    public List<String> imagesList;//图片列表


    public List<String> localImageThumbList;
    public String verify_str = "";
    public Integer sortPosition = 0; //排序位置
    public int isExpand = 0;//  是否为推广字段， 1 为是 ， 0 为不是

    public boolean bTextExpand = false;
    public String recommType = ""; //推广类型

    public int isRecommend = 0; // 是否是推荐说说 1:是，0不是
    public String related; //推荐原由，如:"她他圈聊过的好友",
    public int dynamicNum; // 推荐好友动态数"dynamic_num": 200,
    public int fans; // 推荐好友粉丝数"fans": "20万",
    public int isFollow = 0; // 是否已关注推荐好友
    public int goHomepage;//密友圈首页首推的字段添加字段 go_homepage 等于1 ， 是跳转到个人主页，0 为跳转到她她圈
    public String info; // 圈子小助手推荐提示
    public int isPublic; //is_public  首页的加个这个字段，判断是否是公众账号

    public String toolUrl;//小工具url

    /**
     * 如果type=13，news_type为资讯类型
     */
    public int news_type;

    /**
     * 跳转协议
     */
    public String redirect_url;

    /**
     * 视频的时长
     */
    public String video_time;


    public HomeDynamicModel() {
    }


    public HomeDynamicModel(JSONObject jsonObject) {

        id = StringUtils.getJsonInt(jsonObject, "id");
        verify_str = StringUtils.getJsonString(jsonObject, "verify_original");
        type = StringUtils.getJsonInt(jsonObject, "type");
        news_type = StringUtils.getJsonInt(jsonObject, "news_type");
        redirect_url = StringUtils.getJsonString(jsonObject, "redirect_url");

        userId = StringUtils.getJsonInt(jsonObject, "user_id");
        screenName = StringUtils.getJsonString(jsonObject, "screen_name");
        title = StringUtils.getJsonString(jsonObject, "title");
        content = StringUtils.getJsonString(jsonObject, "content");
        createTime = StringUtils.getJsonString(jsonObject, "created_time");
        updateTime = StringUtils.getJsonString(jsonObject, "updated_date");
        sort = StringUtils.getJsonInt(jsonObject, "sort");
        sortPosition = StringUtils.getJsonInt(jsonObject, "position");

        praiseNum = StringUtils.getJsonInt(jsonObject, "praise_num");
        commentNum = StringUtils.getJsonInt(jsonObject, "comment_num");
        isPraise = StringUtils.getJsonInt(jsonObject, "is_praise");

        iconUrl = StringUtils.getJsonString(jsonObject, "icon");
        typeIcon = StringUtils.getJsonString(jsonObject, "type_icon");
        avatUrl = StringUtils.getJsonString(jsonObject, "id_url_avat");

        publisher = StringUtils.getJsonString(jsonObject, "publisher");
        circleName = StringUtils.getJsonString(jsonObject, "circle_name");
        forumId = StringUtils.getJsonInt(jsonObject, "forum_id");
        idUrl = StringUtils.getJsonString(jsonObject, "id_url");
        isMoreComment = StringUtils.getJsonInt(jsonObject, "is_more");
        shareWords = StringUtils.getJsonString(jsonObject, "words");
        originalId = StringUtils.getJsonInt(jsonObject, "original_id");
        isAllowOperate = StringUtils.getJsonBoolean(jsonObject, "allow_operate");
        isExpand = StringUtils.getJsonInt(jsonObject, "is_expand");
        recommType = StringUtils.getJsonString(jsonObject, "recomm_type");
        tipUrl = StringUtils.getJsonString(jsonObject, "tip_url");
        tipCategory = StringUtils.getJsonString(jsonObject, "tip_category");
        tipCategoryId = StringUtils.getJsonInt(jsonObject, "tip_category_id");
        tipId = StringUtils.getJsonInt(jsonObject, "tip_id");
        topicId = StringUtils.getJsonInt(jsonObject, "topic_id");
        isvip = StringUtils.getJsonInt(jsonObject, "isvip");
        user_type = StringUtils.getJsonInt(jsonObject, "user_type");
        isRecommend = StringUtils.getJsonInt(jsonObject, "is_recommend");
        related = StringUtils.getJsonString(jsonObject, "related");
        dynamicNum = StringUtils.getJsonInt(jsonObject, "dynamic_num");
        fans = StringUtils.getJsonInt(jsonObject, "funs");
        goHomepage = StringUtils.getJsonInt(jsonObject, "go_homepage");
        isFollow = StringUtils.getJsonInt(jsonObject, "is_follow");
        info = StringUtils.getJsonString(jsonObject, "info");
        isPublic = StringUtils.getJsonInt(jsonObject, "is_public");

        toolUrl = StringUtils.getJsonString(jsonObject, "tool_url");
        video_time = StringUtils.getJsonString(jsonObject, "video_time");


        JSONObject avatar = StringUtils.getJsonObejct(jsonObject, "avatar");
        if (null != avatar) {
            avatarModel = new TopicAvatarModel(avatar);
        }


        JSONArray imagesArray = StringUtils.getJsonArray(jsonObject, "images");
        if (null != imagesArray && imagesArray.length() > 0) {
            imagesList = new ArrayList<String>();
            for (int i = 0; i < imagesArray.length(); i++) {
                try {
                    imagesList.add(imagesArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
