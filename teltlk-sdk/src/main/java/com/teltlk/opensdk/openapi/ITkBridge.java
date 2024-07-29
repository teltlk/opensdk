package com.teltlk.opensdk.openapi;

import android.content.Intent;

import com.teltlk.opensdk.model.AuthReq;
import com.teltlk.opensdk.model.PayReq;

public interface ITkBridge {

    boolean handleIntent(Intent intent, ITkBridgeEventHandler eventHandler);

    boolean isTKAppInstalled();

    boolean openTKApp();

    boolean authenticate(AuthReq payReq);
    boolean doPay(PayReq payReq);

    boolean official(String appID, String redirectPath, String argument);

    boolean addFriend(String openID);
    boolean joinGroup(String groupID);
    void detach();

}