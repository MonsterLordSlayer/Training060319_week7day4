package com.example.mapandlocationexample;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 667;
    IPermissionManager iPermissionManager;
    Context context;
    public PermissionManager(Context context){
        this.iPermissionManager=(IPermissionManager)context;
        this.context=context;
    }
    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog ad=new AlertDialog.Builder(context).setTitle("title").setMessage("We need it pls uwu").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
                ad.show();
            } else {
                requestPermission();

            }
        } else {
            iPermissionManager.onPermissionResult(true);

        }
    }

    public void requestPermission() {
        Log.d("TAG", "onCreate: No explanation needed; request the permission");
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    public void checkResult(int requestCode,
                            String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("TAG", "onRequestPermissionsResult: permission was granted");
                    iPermissionManager.onPermissionResult(true);

                } else {

                    iPermissionManager.onPermissionResult(false);

                    Log.d("TAG", "onRequestPermissionsResult: permission denied");

                }
                return;
            }
        }
    }

    public interface IPermissionManager{
        void onPermissionResult(boolean isGranted);
    }
}
