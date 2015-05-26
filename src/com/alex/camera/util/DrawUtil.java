package com.alex.camera.util;

import android.opengl.GLES20;

/**
 * Created by alex on 14-11-8.
 */
public class DrawUtil {
    private static int mPositionLocation = 0;
    private static int COORDS_PER_VERTEX = 2;
    private static int vertexStripe = COORDS_PER_VERTEX<<2;
    private static int mTextureLocation = 0;
    private static float [] vertexs = {
            -1.0f, -1.0f,
            1.0f, -1.0f,
            1.0f, 1.0f,
            -1.0f, 1.0f
    };

    private static float [] textures = {
            0f, 0f,
            1f, 0f,
            1.0f, 1.0f,
            0f, 1.0f
    };

    private static short [] drawOrder = {
            0, 1, 2, 2, 3, 0
    };

    public static void drawRect(){

    }

    public static void draw(int program, int mTexture){
        ShaderUtil.checkError("-3 " + program);
        GLES20.glUseProgram(program);
        ShaderUtil.checkError("-2 " + program);
        boolean is = GLES20.glIsTexture(mTexture);
        ShaderUtil.checkError("-1.5 " + mTexture+ "  " + is);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        ShaderUtil.checkError("-1");
        mPositionLocation = GLES20.glGetAttribLocation(program,"vPosition");
        ShaderUtil.checkError("0");
        GLES20.glEnableVertexAttribArray(mPositionLocation);
        ShaderUtil.checkError("1");
        GLES20.glVertexAttribPointer(mPositionLocation, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                vertexStripe, BufferUtils.toFloatBuffer(vertexs));
        ShaderUtil.checkError("2");
        mTextureLocation = GLES20.glGetAttribLocation(program,"in_texture_coordinate");
        ShaderUtil.checkError("3");
        GLES20.glEnableVertexAttribArray(mTextureLocation);
        ShaderUtil.checkError("4");
        GLES20.glVertexAttribPointer(mTextureLocation, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                vertexStripe, BufferUtils.toFloatBuffer(textures));
        ShaderUtil.checkError("5");
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length ,GLES20.GL_UNSIGNED_SHORT, BufferUtils.toShortBuffer(drawOrder));

        GLES20.glDisableVertexAttribArray(mPositionLocation);
        GLES20.glDisableVertexAttribArray(mTextureLocation);
    }
}
