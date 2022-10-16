package com.reactnativebeecore;

import android.net.Uri;
import android.util.Log;
import android.util.Base64;

import androidx.annotation.NonNull;
import android.net.Uri;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;


import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mobile.Bee;
import mobile.Mobile;

@ReactModule(name = BeeCoreModule.NAME)
public class BeeCoreModule extends ReactContextBaseJavaModule {
    public static final String NAME = "BeeCore";
    Bee core;
    String appDir;
    String storeDirPath;
    String path;


    public BeeCoreModule(ReactApplicationContext reactContext) throws Exception {
        super(reactContext);
        appDir = reactContext.getFilesDir().toString();
        storeDirPath = appDir + "/bee/received/";
        path = appDir + "/bee";
        File storeDir = new File(storeDirPath);
        boolean success = true;
        if (!storeDir.exists()) {
            success = storeDir.mkdirs();
        }
        if(success){
            Log.d(NAME,"store folder created");
        }else{
            Log.d(NAME,"can not create folder");
        }
        this.core = Mobile.newBee(path);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(int a, int b, Promise promise) {
        promise.resolve(a * b);
    }

    public static native int nativeMultiply(int a, int b);
}
