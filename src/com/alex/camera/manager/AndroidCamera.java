package com.alex.camera.manager;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by alex on 14-11-8.
 */
public class AndroidCamera implements ICamera {

    protected Camera mCamera;

    public AndroidCamera(Camera camera){
        mCamera = camera;
    }

    public static void getCameraInfo(int cameraId, Camera.CameraInfo cameraInfo){
        Camera.getCameraInfo(cameraId,cameraInfo);
    }

    public static AndroidCamera open(int cameraId) {
        return new AndroidCamera(Camera.open(cameraId));
    }

    public static Camera.Parameters getEmptyParameters(){
        return null;
    }

    public static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }


    public static ICamera open() {
        return new AndroidCamera(Camera.open());
    }

    @Override
    public void release() {
        mCamera.release();
    }

    @Override
    public void unlock() {
        mCamera.unlock();
    }

    @Override
    public void lock() {
        mCamera.lock();
    }

    @Override
    public void reconnect() throws IOException {
        mCamera.reconnect();
    }

    @Override
    public void setPreviewDisplay(SurfaceHolder holder) throws IOException {
        mCamera.setPreviewDisplay(holder);
    }

    @Override
    public void setPreviewTexture(SurfaceTexture surfaceTexture) throws IOException {
        mCamera.setPreviewTexture(surfaceTexture);
    }

    @Override
    public void startPreview() {
        mCamera.startPreview();
    }

    @Override
    public void stopPreview() {
        mCamera.stopPreview();
    }

    @Override
    public boolean previewEnabled() {
        return false;
    }

    @Override
    public void setPreviewCallback(Camera.PreviewCallback cb) {
        mCamera.setPreviewCallback(cb);
    }

    @Override
    public void setOneShotPreviewCallback(Camera.PreviewCallback cb) {
        mCamera.setOneShotPreviewCallback(cb);
    }

    @Override
    public void setPreviewCallbackWithBuffer(Camera.PreviewCallback cb) {
        mCamera.setPreviewCallbackWithBuffer(cb);
    }

    @Override
    public void addCallbackBuffer(byte[] callbackBuffer) {
        mCamera.addCallbackBuffer(callbackBuffer);
    }

    @Override
    public void addRawImageCallbackBuffer(byte[] callbackBuffer) {
       //TODO this method is set hide in framework. just not use.
    }

    @Override
    public Allocation createPreviewAllocation(RenderScript rs, int usage) {
        //TODO this method is set hide in framework. just not use.
        return null;
    }

    @Override
    public void setPreviewCallbackAllocation(Allocation previewAllocation) {
        //TODO this method is set hide in framework. just not use.
    }

    @Override
    public void autoFocus(Camera.AutoFocusCallback cb) {
        mCamera.autoFocus(cb);
    }

    @Override
    public void cancelAutoFocus() {
        mCamera.cancelAutoFocus();
    }

    @Override
    public void setAutoFocusMoveCallback(Camera.AutoFocusMoveCallback cb) {
        mCamera.setAutoFocusMoveCallback(cb);
    }

    @Override
    public void takePicture(Camera.ShutterCallback shutter, Camera.PictureCallback raw, Camera.PictureCallback jpeg) {
        takePicture(shutter,raw,null,jpeg);
    }

    @Override
    public void takePicture(Camera.ShutterCallback shutter, Camera.PictureCallback raw, Camera.PictureCallback postview, Camera.PictureCallback jpeg) {
        mCamera.takePicture(shutter,raw,postview,jpeg);
    }

    @Override
    public void startSmoothZoom(int value) {
        mCamera.startSmoothZoom(value);
    }

    @Override
    public void stopSmoothZoom() {
        mCamera.stopSmoothZoom();
    }

    @Override
    public void setDisplayOrientation(int degrees) {
        mCamera.setDisplayOrientation(degrees);
    }

    @Override
    public boolean enableShutterSound(boolean enabled) {
        return mCamera.enableShutterSound(enabled);
    }

    @Override
    public void setZoomChangeListener(Camera.OnZoomChangeListener listener) {
        mCamera.setZoomChangeListener(listener);
    }

    @Override
    public void setFaceDetectionListener(Camera.FaceDetectionListener listener) {
        mCamera.setFaceDetectionListener(listener);
    }

    @Override
    public void startFaceDetection() {
        mCamera.startFaceDetection();
    }

    @Override
    public void stopFaceDetection() {
        mCamera.stopFaceDetection();
    }

    @Override
    public void setErrorCallback(Camera.ErrorCallback cb) {
        mCamera.setErrorCallback(cb);
    }

    @Override
    public void setParameters(Camera.Parameters params) {
        mCamera.setParameters(params);
    }

    @Override
    public Camera.Parameters getParameters() {
        return mCamera.getParameters();
    }
}
