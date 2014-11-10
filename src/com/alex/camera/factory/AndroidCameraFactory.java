package com.alex.camera.factory;

import com.alex.camera.manager.AndroidCamera;
import com.alex.camera.manager.ICamera;

/**
 * Created by alex on 14-11-8.
 */
public class AndroidCameraFactory {
    public static ICamera getAndroidCamera(){
        return AndroidCamera.open();
    }

    public static ICamera getAndroidCamera(int cameraId){
        return AndroidCamera.open(cameraId);
    }

}
