package com.alex.camera.renderer;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.alex.camera.manager.ICamera;
import com.alex.camera.util.DrawUtil;
import com.alex.camera.util.LogUtils;
import com.alex.camera.util.ShaderUtil;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * Created by alex on 14-11-8.
 */
public class PreviewFrameRenderer implements GLSurfaceView.Renderer {
    private Context mContext;
    private SurfaceTexture mSurface;
    private float ratio = 0.0f;
    private int program;
    private int mTexture;

    public PreviewFrameRenderer(Context context){
        mContext = context;
        mTexture = createTexName();
        mSurface = new SurfaceTexture(mTexture);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        program = ShaderUtil.createProgram(mContext);
        if(program == 0){
            throw new RuntimeException("Create Program error");
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height == 0){
            height = 1;
        }
        ratio = (float)width/(float)height;
        GLES20.glViewport(0, 0, width, height);
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mSurface.updateTexImage();
        DrawUtil.draw(program,mTexture);
    }

    public SurfaceTexture getSurfaceTexture(){
        return mSurface;
    }

    private int createTexName(){
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        return texture[0];
    }
}
