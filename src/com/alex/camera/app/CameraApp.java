package com.alex.camera.app;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraManager;

/**
 * This application provide a global resource for camera.
 * Created by alex on 15-5-26.
 */
public class CameraApp extends Application implements CameraService {

    protected CameraManager mCameraManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public CameraManager getCameraManager() {
        if (mCameraManager == null) {
            mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        }
        return mCameraManager;
    }
}
