package com.tencent.smtt.export.external.interfaces;

import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PermissionRequest extends android.webkit.PermissionRequest{
    @Override
    public Uri getOrigin() {
        return null;
    }

    @Override
    public String[] getResources() {
        return new String[0];
    }

    @Override
    public void grant(String[] resources) {

    }

    @Override
    public void deny() {

    }
}
