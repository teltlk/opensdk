package com.teltlk.opensdk.model.base;

import android.os.Bundle;

public abstract class BaseReq {

    public String appID;

    public BaseReq() {
    }

    public abstract int getType();


    public void toBundle(Bundle bundle) {
        bundle.putInt("_tkapi_command_type", getType());
        bundle.putString("_tkapi_appid", appID);
    }

    public void fromBundle(Bundle bundle) {
        appID = bundle.getString("_tkapi_appid");
    }


}