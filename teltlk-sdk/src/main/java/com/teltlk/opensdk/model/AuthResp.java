package com.teltlk.opensdk.model;

import android.os.Bundle;

import com.teltlk.opensdk.constants.Constant;
import com.teltlk.opensdk.model.base.BaseResp;


public class AuthResp extends BaseResp {
    public Data data;

    public AuthResp() {
    }

    public AuthResp(Bundle bundle) {
        this.fromBundle(bundle);
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        if (data == null) return;
        bundle.putInt("_tkapi_resp_result", data.result);
        bundle.putString("_tkapi_resp_token", data.token);
        bundle.putLong("_tkapi_resp_expireTime", data.expireTime);
        bundle.putString("_tkapi_resp_lang", data.lang);
        bundle.putString("_tkapi_resp_joinTime", data.joinTime);
        bundle.putString("_tkapi_resp_appID", data.appID);
        bundle.putString("_tkapi_resp_openID", data.openID);
        bundle.putString("_tkapi_resp_faceURL", data.faceURL);
        bundle.putString("_tkapi_resp_nickname", data.nickname);
        bundle.putInt("_tkapi_resp_identity", data.identity);
        bundle.putInt("_tkapi_resp_credit", data.credit);
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        if(data == null) data = new Data();
        data.result = bundle.getInt("_tkapi_resp_result");
        data.token = bundle.getString("_tkapi_resp_token");
        data.expireTime = bundle.getLong("_tkapi_resp_expireTime");
        data.lang = bundle.getString("_tkapi_resp_lang");
        data.joinTime = bundle.getString("_tkapi_resp_joinTime");
        data.appID = bundle.getString("_tkapi_resp_appID");
        data.openID = bundle.getString("_tkapi_resp_openID");
        data.faceURL = bundle.getString("_tkapi_resp_faceURL");
        data.nickname = bundle.getString("_tkapi_resp_nickname");
        data.identity = bundle.getInt("_tkapi_resp_identity");
        data.credit = bundle.getInt("_tkapi_resp_credit");
    }

    public int getType() {
        return Constant.authCommandType;
    }

    public static class Data {

        public int result;
        public String token;
        public long expireTime;
        public String lang;
        public String joinTime;
        public String appID;
        public String openID;
        public String faceURL;
        public String nickname;
        public int identity;
        public int credit;
    }
}
