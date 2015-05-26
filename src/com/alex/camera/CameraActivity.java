package com.alex.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.graphics.SurfaceTexture;
import android.widget.FrameLayout;
import com.alex.camera.factory.AndroidCameraFactory;
import com.alex.camera.manager.ICamera;
import com.alex.camera.ui.PreviewFrame;
import com.alex.camera.util.LogUtils;
import com.alex.camera.util.Utils;

import java.io.IOException;

public class CameraActivity extends Activity {

    private static String TAG = "CameraActivity";
    private ICamera mCamera;
    private Intent mIntent;

    private PreviewFrame mPreviewFrame;

    private Camera.Parameters mParameters;
    private FrameLayout mRootView = null;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        setContentView(R.layout.main);
        initComponent();
    }
    private void initComponent(){
        mRootView = (FrameLayout)findViewById(R.id.id_camera_root_view);
        mPreviewFrame = new PreviewFrame(this);
        mRootView.addView(mPreviewFrame);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mCamera == null){
//            new Thread(mStartCameraThread).start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    private Runnable mStartCameraThread = new Runnable() {
        @Override
        public void run() {
            int cameraId = Utils.getCameraId(null, mIntent);
            mCamera = AndroidCameraFactory.getAndroidCamera(cameraId);
            mParameters = mCamera.getParameters();
            try {
                mCamera.setPreviewTexture(mPreviewFrame.getSurfaceTexture());
                mCamera.startPreview();
            }catch (IOException e){
                LogUtils.e(TAG,"setPreviewTexture error",e);
                finish();
            }
        }
    };

    public void startPreview(){
        int cameraId = Utils.getCameraId(null, mIntent);
        mCamera = AndroidCameraFactory.getAndroidCamera(cameraId);
        mParameters = mCamera.getParameters();
        try {
            mCamera.setPreviewTexture(mPreviewFrame.getSurfaceTexture());
            mCamera.startPreview();
        }catch (IOException e){
            LogUtils.e(TAG,"setPreviewTexture error",e);
            finish();
        }
    }
}
