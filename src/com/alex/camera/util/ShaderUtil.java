package com.alex.camera.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

/**
 * Created by alex on 14-11-8.
 */
public class ShaderUtil {
    private static String TAG = "ShaderUtil";

    private static String VERTEX_SHADER_SOURCES = "previewShader/shader.vp";
    private static String FRAGMENT_SHADER_SOURCES = "previewShader/shader.fp";

    public static String getString(Context context, String fileName){
        String result = null;
        AssetManager manager = context.getAssets();
        try {
            InputStream in = manager.open(fileName);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read = -1;
            while((read = in.read()) != -1){
                out.write(read);
            }
            byte [] bytes = out.toByteArray();
            in.close();
            out.close();
            result = new String(bytes, Charset.forName("UTF-8"));
            result = result.replaceAll("\\r\\n", "\\n");
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    private static int createShader(int shaderType, String sources){
        int shader = 0;
        if(shaderType != GLES20.GL_VERTEX_SHADER && shaderType != GLES20.GL_FRAGMENT_SHADER){
            LogUtils.d(TAG, "Not Support for the shader type:" + shaderType);
            return 0;
        }
        shader = GLES20.glCreateShader(shaderType);
        GLES20.glShaderSource(shader, sources);
        GLES20.glCompileShader(shader);
        int [] comStatus = new int[1];
        GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,comStatus,0);
        if(comStatus[0] != GLES20.GL_COMPILE_STATUS){
            String pre = "";
            if(shaderType == GLES20.GL_VERTEX_SHADER){
                pre = "Compile Vertex Shader Error : ";
            }else{
                pre = "Compile Fragment Shader Error : ";
            }
            Log.e(TAG,pre + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
            throw new RuntimeException(pre);
        }
        return shader;
    }

    public static void checkError(String message){
        int error = 0;
        if((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR){
            String errorMessage = GLES20.glGetString(error);
            LogUtils.e(TAG,message + " " + error +" :" + errorMessage);
        }
    }

    public static int createProgram(Context context){
        int program = 0;

        int vertexShader = createShader(GLES20.GL_VERTEX_SHADER, getString(context,VERTEX_SHADER_SOURCES));
        int fragmentShader = createShader(GLES20.GL_FRAGMENT_SHADER, getString(context,FRAGMENT_SHADER_SOURCES));

        if((vertexShader & fragmentShader) == 0){
            LogUtils.e(TAG,"Compile Shader Error");
            return program;
        }

        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        int [] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS,linkStatus,0);
        if(linkStatus[0] != GLES20.GL_COMPILE_STATUS){
            LogUtils.e(TAG,"Link Error:" + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            program = 0;
        }
        return program;
    }
}
