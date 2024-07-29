package com.teltlk.opensdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.teltlk.opensdk.constants.Constant;
import com.teltlk.opensdk.model.AuthReq;
import com.teltlk.opensdk.model.AuthResp;
import com.teltlk.opensdk.model.PayReq;
import com.teltlk.opensdk.model.PayResp;
import com.teltlk.opensdk.util.Utils;

final class TkBridge implements ITkBridge {
    protected Context context;
    protected boolean checkSignature = true;
    private static final String TAG = "tkBridge";

    TkBridge(Context context) {
        this.context = context;
    }

    @Override
    public boolean handleIntent(Intent intent, ITkBridgeEventHandler eventHandler) {
        if (!TkBridgeImplComm.isIntentFromTK(intent, "com.teltlk.app.openapi.token")) {
            Log.i(TAG, "handleIntent fail, intent not from TELTLK msg");
            return false;
        } else if (context == null) {
            Log.i(TAG, "handleIntent fail, tkBridge has been detached");
            return false;
        }
        int cmd = intent.getIntExtra("_tkapi_command_type", 0);
        Log.i(TAG, "handleIntent, cmd = " + cmd);
        try {
            switch (cmd) {
                case Constant.authCommandType:
                    eventHandler.onResp(new AuthResp(intent.getBundleExtra("tk_bundle")));
                    return true;
                case Constant.payCommandType:
                    eventHandler.onResp(new PayResp(intent.getBundleExtra("tk_bundle")));
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isTKAppInstalled() {
        if (context == null) {
            Log.i(TAG, "TKAPI has been detached");
            return false;
        }
        try {
            return TkBridgeImplComm.validateAppSignatureForPackage(context, Constant.packageName, checkSignature);

        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean openTKApp() {
        if (context == null) {
            Log.i(TAG, "openTKApp fail, tkBridge has been detached");
            return false;
        }
        if (isTKAppInstalled()) {
            try {
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(Constant.packageName));
                return true;
            } catch (Exception e) {
                Log.e(TAG, "startActivity fail, exception = " + e.getMessage());
                return false;
            }
        } else {
            Log.e(TAG, "open TELTLK app failed, not installed or signature check failed");
            return false;
        }
    }


    @Override
    public void detach() {
        this.context = null;
    }

    @Override
    public boolean authenticate(AuthReq req) {
        if (context == null) {
            Log.i(TAG, "authenticate fail, tkBridge has been detached");
            return false;
        } else if (!TkBridgeImplComm.validateAppSignatureForPackage(context, Constant.packageName, checkSignature)) {
            Log.e(TAG, "authenticate failed for TELTLK app signature check failed");
            return false;
        } else {
            Bundle bundle = new Bundle();
            req.toBundle(bundle);
            return send(Constant.authCommandType, bundle);
        }
    }

    @Override
    public boolean doPay(PayReq req) {
        if (context == null) {
            Log.i(TAG, "doPay fail, tkBridge has been detached");
            return false;
        } else if (!TkBridgeImplComm.validateAppSignatureForPackage(context, Constant.packageName, this.checkSignature)) {
            Log.e(TAG, "doPay failed for TELTLK app signature check failed");
            return false;
        } else {
            Bundle bundle = new Bundle();
            req.toBundle(bundle);
            return send(Constant.payCommandType, bundle);
        }
    }

    @Override
    public boolean official(String appID, String redirectPath, String argument) {
        if (Utils.isEmpty(appID)) {
            Log.e(TAG, "openOfficial failed for AppID cannot be null");
            return false;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tk://teltlk.com/official?appID=" + appID + "&redirectPath=" + redirectPath + "&argument=" + argument));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "openOfficial fail, ex = " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addFriend(String openID) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tk://teltlk.com/chat?openID=" + openID));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "toSingleChat fail, ex = " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean joinGroup(String groupID) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tk://teltlk.com/chat?groupID=" + groupID));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "toGroupChat fail, ex = " + e.getMessage());
            return false;
        }
    }

    private boolean send(int commandType, Bundle bundle) {
        if (context == null) {
            Log.i(TAG, "send fail, tkBridge has been detached");
            return false;
        }
        try {
            Intent intent = new Intent(Constant.action);
            intent.putExtras(bundle);

            intent.putExtra("_tkapi_command_type", commandType);
            intent.putExtra("_tkapi_sdkVersion", Constant.sdkVersion);
            intent.putExtra("_tkapi_appPackage", context.getPackageName());
            context.startActivity(intent);
            Log.d(TAG, "send mm message, intent=" + intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "send fail, ex = " + e.getMessage());
            return false;
        }
    }

}