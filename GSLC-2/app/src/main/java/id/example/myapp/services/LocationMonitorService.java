package id.example.myapp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import id.example.myapp.R;

public class LocationMonitorService extends Service {

    private LocationManager locationManager;
    private static final String CHANNEL_ID = "channel_location_service";
    private static final int NOTIFICATION_ID_FOREGROUND = 123;
    private static final int NOTIFICATION_ID_ALERT = 999;

    // Binus Alam Sutera Coordinate
    private static final double TARGET_LAT = -6.2236284;
    private static final double TARGET_LONG = 106.64921960680044;

    private boolean hasNotified = false;

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        hasNotified = false;

        // Foreground Service
        startForeground(NOTIFICATION_ID_FOREGROUND, getForegroundNotification());

        startTracking();

        Toast.makeText(this, "Service Start and Reset...", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    private void startTracking() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

            if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            }

            Location lastKnownGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownGPS != null) processLocation(lastKnownGPS);

            Location lastKnownNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (lastKnownNet != null) processLocation(lastKnownNet);
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location currentLocation) {
            processLocation(currentLocation);
        }

        @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
        @Override public void onProviderEnabled(@NonNull String provider) {}
        @Override public void onProviderDisabled(@NonNull String provider) {}
    };

    private void processLocation(Location currentLocation) {
        float[] results = new float[1];
        Location.distanceBetween(
                currentLocation.getLatitude(), currentLocation.getLongitude(),
                TARGET_LAT, TARGET_LONG,
                results);

        float distanceInMeters = results[0];
        Log.d("LOC_SERVICE", "Jarak: " + distanceInMeters);

        // Notification Logic (< 100 meter)
        if (distanceInMeters < 100) {
            if (!hasNotified) {
                triggerBinusNotification(distanceInMeters);
                hasNotified = true;
            }
        }

        else if (distanceInMeters > 200) {
            hasNotified = false;
        }

    }

    private void triggerBinusNotification(float distance) {
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_binus);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setLargeIcon(largeIcon)
                .setContentTitle("Welcome to Binus Alam Sutera!")
                .setContentText("You already arrived! Distance: " + String.format("%.0f", distance) + "m")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(NOTIFICATION_ID_ALERT, builder.build());
    }

    private Notification getForegroundNotification() {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Monitor Active")
                .setContentText("Searching your location...")
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setOngoing(true)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Location Monitor Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}