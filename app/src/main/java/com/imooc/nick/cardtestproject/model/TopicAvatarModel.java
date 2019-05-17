package com.imooc.nick.cardtestproject.model;



import com.imooc.nick.cardtestproject.Util.StringUtils;
import org.json.JSONObject;
import java.io.Serializable;


public class TopicAvatarModel implements Serializable {
    private static final long serialVersionUID = 12308L;
    public String large;
    public String medium;
    public String small;

    public TopicAvatarModel() {
    }

    public TopicAvatarModel(JSONObject jsonObject) {
        try {
            this.large = StringUtils.getJsonString(jsonObject, "large");
            this.medium = StringUtils.getJsonString(jsonObject, "medium");
            this.small = StringUtils.getJsonString(jsonObject, "small");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public TopicAvatarModel(String medium) {
        try {
            this.medium = medium;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}
