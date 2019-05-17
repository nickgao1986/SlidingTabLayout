package com.imooc.nick.cardtestproject.api;

import com.http.api.ApiUtil;

import org.json.JSONObject;

public class DynamicApi extends ApiUtil {


    public DynamicApi() {

    }

    @Override
    protected String getUrl() {
        return "http://my_dynamic";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {

    }
}
