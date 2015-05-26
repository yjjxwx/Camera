package com.alex.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * This activity provide more controls for subclass.
 * Created by alex on 15-5-26.
 */
public class BaseActivity extends Activity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateTask(savedInstanceState);
    }


    protected void onCreateTask(Bundle savedInstanceState) {

    }


    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onNewIntentTask(intent);
    }

    protected void onNewIntentTask(Intent intent) {

    }

    @Override
    protected final void onStart() {
        super.onStart();
        onStartTask();
    }

    protected void onStartTask() {

    }


    @Override
    protected final void onRestart() {
        super.onRestart();
        onRestartTask();
    }

    protected void onRestartTask() {

    }

    @Override
    protected final void onResume() {
        super.onResume();
        onResumeTask();
    }

    protected void onResumeTask() {

    }

    @Override
    protected final void onPause() {
        super.onPause();
        onPauseTask();
    }

    protected void onPauseTask() {

    }

    @Override
    protected final void onStop() {
        super.onStop();
        onStopTask();
    }

    protected void onStopTask() {

    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        onDestroyTask();
    }

    protected void onDestroyTask() {

    }


}
