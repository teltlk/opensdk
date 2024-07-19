package com.teltlk.opensdk.model.base;


import android.os.Bundle;

public abstract class BaseResp {
    public int errCode;
    public String errMsg;

    public BaseResp() {
    }

    public abstract int getType();

    public void toBundle(Bundle bundle) {
        bundle.putInt("_tkapi_command_type", this.getType());
        bundle.putInt("_tkapi_baseresp_errcode", this.errCode);
        bundle.putString("_tkapi_baseresp_errmsg", this.errMsg);
    }

    public void fromBundle(Bundle bundle) {
        this.errCode = bundle.getInt("_tkapi_baseresp_errcode");
        this.errMsg = bundle.getString("_tkapi_baseresp_errmsg");
    }

    public interface ErrCode {
        int ERR_OK = 0;

    }
}