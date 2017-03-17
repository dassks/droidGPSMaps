package me.pdant.mapapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by pdant on 17/03/2017.
 * <p>
 * Will handle locations and gps. Using this class we can get the current location using various methods.
 * Permissions will also be handled here as different permissions are important
 */

public class Locations implements LocationListener {

    private static final String LOG_TAG = "Locations";

    private LocationManager locationManager;
    private Activity activity;
    private PermissionUtil permissionUtil;

    public Locations(Context context) {
        // Give context to the ContextWrapper class (May not be needed because of getApplicationClass())
        activity = (Activity) context;

        // Setup permissionUtil class
        permissionUtil = new PermissionUtil(Manifest.permission.ACCESS_FINE_LOCATION, "To locate user", this.activity);

        // Create the location manager to get the context location services
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (checkLocationPermission()) {
            Log.i(LOG_TAG, "Settings up location updates");
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOG_TAG, "onLocationChanged");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(LOG_TAG, "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(LOG_TAG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(LOG_TAG, "onProviderDisabled");
    }

    private boolean checkLocationPermission() {
        return permissionUtil.checkPermission();
    }


}
