package com.alex.camera.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;

/**
 * Created by alex on 14-11-8.
 */
public class Utils {

    public static int getCameraId(SharedPreferences sharedPreferences, Intent intent){
        return Camera.CameraInfo.CAMERA_FACING_BACK;
    }
}
