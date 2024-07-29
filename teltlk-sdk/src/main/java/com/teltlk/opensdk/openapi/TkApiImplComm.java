package com.teltlk.opensdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.util.Log;

import com.teltlk.opensdk.constants.Constant;

class TkApiImplComm {
    private static final String TAG = "TK.SDK.TKMsgImplComm";

    private TkApiImplComm() {

    }

    public static boolean isIntentFromTK(Intent intent, String key) {
        if (intent == null) {
            return false;
        } else {
            return key.equals(intent.getStringExtra("tk_token_key"));
        }
    }

    public static boolean validateAppSignatureForPackage(Context context, String packageName, boolean needValidate) {
        if (!needValidate) {
            Log.d(TAG, "ignore TELTLK app signature validation");
            return true;
        } else {
            try {
                PackageManager packageManager = context.getPackageManager();
                Signature[] signatures;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {   // API Level 28 及以上
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES);
                    SigningInfo signingInfo = packageInfo.signingInfo;
                    if (signingInfo.hasMultipleSigners()) {
                        signatures = signingInfo.getApkContentsSigners();
                    } else {
                        signatures = signingInfo.getSigningCertificateHistory();
                    }
                } else {    // API Level 28 以下
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                    signatures = packageInfo.signatures;
                }
                return validateAppSignature(signatures, true);
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static boolean validateAppSignature(Signature[] signatures, boolean needValidate) {
        if (!needValidate) {
            Log.d(TAG, "ignore TELTLK app signature validation");
            return true;
        } else {
            for (Signature signature : signatures) {
                if (signature != null) {
                    String signLower = signature.toCharsString().toLowerCase();
                    Log.d(TAG, "check signature:" + signLower);
                    if (signLower.equals(Constant.TK_APP_SIGNATURE) || signLower.equals(Constant.TK_APP_SIGTK_APP_SIGNATURE_GOOGLENATURE)) {
                        Log.d(TAG, "pass");
                        return true;
                    }
                }
            }
            return false;
        }
    }
}