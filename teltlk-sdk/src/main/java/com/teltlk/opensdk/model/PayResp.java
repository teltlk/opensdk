package com.teltlk.opensdk.model;

import android.os.Bundle;

import com.teltlk.opensdk.model.base.BaseResp;

public class PayResp extends BaseResp {
    public Data data = new Data();

    public PayResp() {
    }

    public PayResp(Bundle bundle) {
        this.fromBundle(bundle);
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putInt("_tkapi_data", this.data.result);
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.data.result = bundle.getInt("_tkapi_data");
    }

    public int getType() {
        return 2;
    }

    public static class Data{

        public int result;
    }
}