package com.alex.camera.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.alex.camera.R;
import com.alex.camera.util.BufferUtils;
import com.alex.camera.util.ShaderUtil;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


public class LiveFilterItemRenderer extends TextureSurfaceRenderer implements
        SurfaceTexture.OnFrameAvailableListener {

    public static final String TAG = LiveFilterItemRenderer.class.getSimpleName();

    private SurfaceTexture mInputSurfaceTexture;

    private static float squareSize = 0.8f;
    private static float squareCoords[] = {
            -squareSize, -squareSize, 0.0f,   // bottom left
            -squareSize, squareSize, 0.0f,   // top left
            squareSize, squareSize, 0.0f, // top right
            squareSize, -squareSize, 0.0f};   // bottom right


   // private static short drawOrder[] = {0, 1, 2, 0, 2, 3};

    private Context context;

    // Texture to be shown in backgrund
    private FloatBuffer textureBuffer;
    private float textureCoords[] = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
            };
    private int[] mTexture = new int[1];

    private int shaderProgram;
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;


    private float[] videoTextureTransform;
    private boolean frameAvailable = false;


    public LiveFilterItemRenderer(Context context, SurfaceTexture texture, int width, int height) {
        super(texture, width, height);
        this.context = context;
        videoTextureTransform = new float[16];
    }

    private void loadShaders() {
        final String vertexShader = ShaderUtil.loadFromAssetsFile("previewShader/vertex_shader.glsl", context.getResources());
        final String fragmentShader = ShaderUtil.loadFromAssetsFile("previewShader/fragment_shader.glsl", context.getResources());
        shaderProgram = ShaderUtil.createProgram(vertexShader, fragmentShader);
    }

    private void setupVertexBuffer() {
        // Draw list buffer
       // drawListBuffer = BufferUtils.toShortBuffer(drawOrder);

        // Initialize the texture holder
        vertexBuffer = BufferUtils.toFloatBuffer(squareCoords);
    }

    private void setupTexture() {
        Log.d("yjjxwx", "setup Texture");
        textureBuffer = BufferUtils.toFloatBuffer(textureCoords);
        // Generate the actual texture
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        GLES20.glGenTextures(1, mTexture, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture[0]);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        checkGlError("Texture generate");
        if (mInputSurfaceTexture == null) {
           // mInputSurfaceTexture = new SurfaceTexture(mTexture[0]);
        }
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        bmp.recycle();
        checkGlError("Texture bind");
    }

    @Override
    protected boolean onDraw() {
        /*
        synchronized (this) {
            if (frameAvailable) {
                //mOutputSurfaceTexture.updateTexImage();
                //mOutputSurfaceTexture.getTransformMatrix(videoTextureTransform);
                frameAvailable = false;
            } else {
                return false;
            }

        }
        */
       // Log.d("yjjxwx", "onDrawing .....");
        synchronized (mLock) {

            GLES20.glClearColor(1.0f, 1.0f, 0.0f, 0.2f);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            GLES20.glViewport(0, 0, width, height);
        //    mInputSurfaceTexture.attachToGLContext(mTexture[0]);
         //   mInputSurfaceTexture.updateTexImage();
            this.drawTexture();
          //  mInputSurfaceTexture.detachFromGLContext();
        }

        return true;
    }

    private void drawTexture() {
        // Draw texture
        GLES20.glUseProgram(shaderProgram);
        int textureParamHandle = GLES20.glGetUniformLocation(shaderProgram, "texture");
        int textureCoordinateHandle = GLES20.glGetAttribLocation(shaderProgram, "vTexCoordinate");
        int positionHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        //int textureTranformHandle = GLES20.glGetUniformLocation(shaderProgram, "textureTransform");

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture[0]);
        GLES20.glUniform1i(textureParamHandle, 0);

        GLES20.glEnableVertexAttribArray(textureCoordinateHandle);
        GLES20.glVertexAttribPointer(textureCoordinateHandle, 2, GLES20.GL_FLOAT, false, 0, textureBuffer);

       //GLES20.glUniformMatrix4fv(textureTranformHandle, 1, false, videoTextureTransform, 0);

       // GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length / 3, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 4);
       // GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 12);
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(textureCoordinateHandle);
    }


    @Override
    protected void initGLComponents() {
        setupVertexBuffer();
        setupTexture();
        loadShaders();
    }

    @Override
    protected void deinitGLComponents() {
        GLES20.glDeleteTextures(1, mTexture, 0);
        GLES20.glDeleteProgram(shaderProgram);
        mOutputSurfaceTexture.release();
        mOutputSurfaceTexture.setOnFrameAvailableListener(null);
    }


    public void checkGlError(String op) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("SurfaceTest", op + ": glError " + GLUtils.getEGLErrorString(error));
        }
    }

    @Override
    public SurfaceTexture getOutputSurfaceTexture() {
        return mOutputSurfaceTexture;
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.d("yjjxwx", "Live FilterItem Renderer: onFrameAvailable");
        synchronized (this) {
            frameAvailable = true;
        }
    }
}
