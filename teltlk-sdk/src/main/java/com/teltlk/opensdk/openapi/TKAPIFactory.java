package com.teltlk.opensdk.openapi;

import android.content.Context;
import android.util.Log;

public class TKAPIFactory {

    public static ITKAPI createTKAPI(Context context) {
        return new TKApi(context);
    }

    private TKAPIFactory() {
        throw new RuntimeException(TKAPIFactory.class.getSimpleName() + " should not be instantiated");
    }
}