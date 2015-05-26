package com.alex.camera.app;

import android.hardware.camera2.CameraManager;
import android.os.Handler;


/**
 * Created by alex on 15-5-26.
 */
public interface CameraService {
    /**
     * Get the CameraManager instance.
     * @return a camera manager instance.
     */
    public CameraManager getCameraManager();
}
