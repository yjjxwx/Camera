package com.alex.camera;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import com.alex.camera.filter.LiveFilterItemRenderer;
import com.alex.camera.filter.TextureSurfaceRenderer;

public class MainActivity extends Activity implements TextureView.SurfaceTextureListener,
        MediaPlayer.OnPreparedListener{


    public String videoPath = Environment.getExternalStorageDirectory().getPath()+"/vido.3gp";
    private TextureView textureView;
    private MediaPlayer mediaPlayer;

    private TextureSurfaceRenderer videoRenderer;
    private int surfaceWidth;
    private int surfaceHeight;
    private Surface surface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureView = (TextureView) findViewById(R.id.id_textureview);
        textureView.setSurfaceTextureListener(this);

    }

    private void playVideo() {
        if (mediaPlayer == null) {
            Log.d("yjjxwx", "Texture playvideo");
            videoRenderer = new LiveFilterItemRenderer(this, textureView.getSurfaceTexture(), surfaceWidth, surfaceHeight);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        try {
            if (mp != null) {
                mp.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (textureView.isAvailable()) {
            playVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.d("yjjxwx", "MainActivity: onSurfaceTextureAvailable: " + width + " x " + height);
        surfaceWidth = width;
        surfaceHeight = height;

        playVideo();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
