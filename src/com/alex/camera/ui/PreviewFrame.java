package com.alex.camera.ui;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.Surface;
import com.alex.camera.CameraActivity;
import com.alex.camera.renderer.PreviewFrameRenderer;
import com.alex.camera.util.LogUtils;
import com.alex.camera.util.ShaderUtil;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by alex on 14-11-8.
 */
public class PreviewFrame extends GLSurfaceView implements SurfaceTexture.OnFrameAvailableListener{

    private static final String TAG = "PreviewFrame";

    private PreviewFrameRenderer mRenderer;

    private SurfaceTexture mSurfaceTexture;

    private int mTextureId = -1;

    public PreviewFrame(Context context) {
        this(context, null);
    }

    public PreviewFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
        mSurfaceTexture = new SurfaceTexture(mTextureId);
        mRenderer = new PreviewFrameRenderer(this, mSurfaceTexture);
        mSurfaceTexture.setOnFrameAvailableListener(this);
        setRenderer(mRenderer);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.requestRender();
    }
    public SurfaceTexture getSurfaceTexture(){
        return mRenderer.getSurfaceTexture();
    }

    public void startPreview(){
        ((CameraActivity)getContext()).startPreview();
    }

}
