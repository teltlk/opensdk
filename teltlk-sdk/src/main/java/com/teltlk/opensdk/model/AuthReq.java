package com.teltlk.opensdk.model;

import android.os.Bundle;

import com.teltlk.opensdk.model.base.BaseReq;

public class AuthReq extends BaseReq {
    private static final String TAG = "TK.PaySdk.PayReq";
    public String nonce;
    public String sign;
    public String timeStamp;

    public AuthReq() {
    }

    public AuthReq(String appID, String nonce, String sign, String timeStamp) {
        this.appID = appID;
        this.nonce = nonce;
        this.sign = sign;
        this.timeStamp = timeStamp;
    }

    @Override
    public int getType() {
        return 1;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_tkapi_authreq_nonce", this.nonce);
        bundle.putString("_tkapi_authreq_sign", this.sign);
        bundle.putString("_tkapi_authreq_timeStamp", this.timeStamp);
    }

    public void fromBundle(Bundle bundle) {
        this.nonce = bundle.getString("_tkapi_authreq_nonce");
        this.sign = bundle.getString("_tkapi_authreq_sign");
        this.timeStamp = bundle.getString("_tkapi_payreq_timestamp");

    }
}