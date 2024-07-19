package com.teltlk.opensdk.model;

import android.os.Bundle;

import com.teltlk.opensdk.model.base.BaseReq;

public class PayReq extends BaseReq {
    private static final String TAG = "TK.PaySdk.PayReq";
    private static final int EXTDATA_MAX_LENGTH = 1024;
    public int currencyID;
    public String amount;
    public String orderID;
    public String timeStamp;
    public String callbackUrl;

    public PayReq(String appID, int currencyID, String amount, String orderID, String timeStamp, String callbackUrl) {
        this.appID = appID;
        this.currencyID = currencyID;
        this.amount = amount;
        this.orderID = orderID;
        this.timeStamp = timeStamp;
        this.callbackUrl = callbackUrl;
    }

    @Override
    public int getType() {
        return 2;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putInt("_tkapi_payreq_currencyID", this.currencyID);
        bundle.putString("_tkapi_payreq_amount", this.amount);
        bundle.putString("_tkapi_payreq_orderID", this.orderID);
        bundle.putString("_tkapi_payreq_timestamp", this.timeStamp);
        bundle.putString("_tkapi_payreq_callbackUrl", this.callbackUrl);
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.currencyID = bundle.getInt("_tkapi_payreq_currencyID");
        this.amount = bundle.getString("_tkapi_payreq_amount");
        this.orderID = bundle.getString("_tkapi_payreq_orderID");
        this.timeStamp = bundle.getString("_tkapi_payreq_timestamp");
        this.callbackUrl = bundle.getString("_tkapi_payreq_callbackUrl");
    }


}