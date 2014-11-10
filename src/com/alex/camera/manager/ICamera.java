package com.alex.camera.manager;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.security.Policy;

/**
 * This interface to defined all of frameworks camera's actions.
 * So we can use is in our hands
 */
public interface ICamera {

    public void release();

    public void unlock();

    public void lock();

    public void reconnect() throws IOException;

    public void setPreviewDisplay(SurfaceHolder holder) throws IOException;

    public void setPreviewTexture(SurfaceTexture surfaceTexture) throws IOException;

    public void startPreview();

    public void stopPreview();

    /**
     * hide in framework.
     * @return
     */
    public boolean previewEnabled();

    public void setPreviewCallback(Camera.PreviewCallback cb);

    public void setOneShotPreviewCallback(Camera.PreviewCallback cb);

    public void setPreviewCallbackWithBuffer(Camera.PreviewCallback cb);

    public void addCallbackBuffer(byte[] callbackBuffer);

    /**
     * Hide in framework
     * @param callbackBuffer
     */
    public void addRawImageCallbackBuffer(byte[] callbackBuffer);

    /**
     * Hide in framework.
     * @param rs
     * @param usage
     * @return
     */
    public Allocation createPreviewAllocation(RenderScript rs, int usage);

    /**
     * Hide in framework.
     * @param previewAllocation
     */
    public void setPreviewCallbackAllocation(Allocation previewAllocation);

    public void autoFocus(Camera.AutoFocusCallback cb);

    public void cancelAutoFocus();

    public void setAutoFocusMoveCallback(Camera.AutoFocusMoveCallback cb);

    public void takePicture(Camera.ShutterCallback shutter, Camera.PictureCallback raw,
                                  Camera.PictureCallback jpeg);

    public void takePicture(Camera.ShutterCallback shutter, Camera.PictureCallback raw,
                                  Camera.PictureCallback postview, Camera.PictureCallback jpeg);

    public void startSmoothZoom(int value);

    public void stopSmoothZoom();

    public void setDisplayOrientation(int degrees);

    public boolean enableShutterSound(boolean enabled);

    public void setZoomChangeListener(Camera.OnZoomChangeListener listener);

    public void setFaceDetectionListener(Camera.FaceDetectionListener listener);

    public void startFaceDetection();

    public void stopFaceDetection();

    public void setErrorCallback(Camera.ErrorCallback cb);

    public void setParameters(Camera.Parameters params);

    public Camera.Parameters getParameters();

}
