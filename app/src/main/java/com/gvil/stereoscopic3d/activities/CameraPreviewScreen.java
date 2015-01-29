package com.gvil.stereoscopic3d.activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.gvil.stereoscopic3d.R;
import com.gvil.stereoscopic3d.utilities.CameraSurfacePreview;

@SuppressWarnings("deprecation")
public class CameraPreviewScreen extends Activity {

    private final static String TAG = "Camera Preview Screen";

    private Camera mCamera;
    private CameraSurfacePreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);
        loadCameraAndPreview();
    }


    /* load camera if it is available */
    private void loadCameraAndPreview() {

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            try {
                mCamera = Camera.open(0);

                mPreview = new CameraSurfacePreview(this, mCamera);
                FrameLayout preview = (FrameLayout) findViewById(R.id.preview_frame);
                preview.addView(mPreview);

            } catch (Exception e) {
                Log.e(TAG, "Camera unable to open");
            }



        } else {
            Log.e(TAG, "Camera not available on this device");
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_preview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
