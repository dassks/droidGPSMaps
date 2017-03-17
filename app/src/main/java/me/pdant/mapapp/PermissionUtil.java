package me.pdant.mapapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by pdant on 17/03/2017.
 *
 * Permissions util class can check whether a permission is already granted and if not it can handle
 * requesting permission from the user.
 */

public class PermissionUtil implements ActivityCompat.OnRequestPermissionsResultCallback {

    private Manifest.permission permission;
    private String reason;
    private Activity activity;

    private static final int PERMISSION_REQUEST_FINE_LOC = 986;

    // Will be true if the permission has been requested already
    public boolean hasPermission = false;


    public PermissionUtil(Manifest.permission perm, String reason, Context context){
        permission = perm;
        this.reason = reason;
        activity = (Activity) context;

        if(checkPermission()){
            hasPermission = true;
        } else {
            hasPermission = false;
            requestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    public void requestPermission(){
        /*int permissionCheck = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)){
                // Give an explanation to the user *async* about the permissions
                Log.i("checkLocationPermission", "User needs an explanation of the permissions");
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_REQUEST_FINE_LOC);
                return true;
            }
        }*/
    }

    public boolean checkPermission(){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }
}
