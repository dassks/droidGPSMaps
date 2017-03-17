package me.pdant.mapapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by pdant on 17/03/2017.
 *
 * Will handle locations and gps. Using this class we can get the current location using various methods.
 * Permissions will also be handled here as different permissions are important
 *
 */

public class Locations implements LocationListener {

    private static final int PERMISSION_REQUEST_FINE_LOC = 368;
    
    private LocationManager locationManager;
    private Activity activity;

    public Locations(Context context){
        // Give context to the ContextWrapper class (May not be needed because of getApplicationClass())
        activity = (Activity) context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(checkLocationPermission()){
            Log.i("Locations Class", "Settings up location updates");
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Locations Class", "onLocationChanged");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("Locations Class", "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("Locations Class", "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("Locations Class", "onProviderDisabled");
    }

    private boolean checkLocationPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)){
                // Give an explaination to the user *async* about the permissions
                Log.i("checkLocationPermission", "User needs an explaination of the permissions");
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_REQUEST_FINE_LOC);
                return true;
            }
        }
        return false;
    }


}
