package com.teltlk.opensdk.openapi;

import android.content.Context;

public class TkBridgeFactory {

    public static ITkBridge createTKAPI(Context context) {
        return new TkBridge(context);
    }

    private TkBridgeFactory() {
        throw new RuntimeException(TkBridgeFactory.class.getSimpleName() + " should not be instantiated");
    }
}