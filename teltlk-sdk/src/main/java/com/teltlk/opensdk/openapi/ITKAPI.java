package com.teltlk.opensdk.openapi;

import android.content.Intent;

import com.teltlk.opensdk.model.AuthReq;
import com.teltlk.opensdk.model.PayReq;

public interface ITKAPI {

    boolean handleIntent(Intent intent, ITKAPIEventHandler eventHandler);

    boolean isTKAppInstalled();

    boolean openTKApp();

    boolean authenticate(AuthReq payReq);
    boolean doPay(PayReq payReq);

    boolean openOfficial(String appID, String redirectPath, String argument);

    boolean toSingleChat(String openID);
    boolean toGroupChat(String groupID);
    void detach();

}