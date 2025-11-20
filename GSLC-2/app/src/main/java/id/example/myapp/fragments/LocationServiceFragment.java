package id.example.myapp.fragments;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import id.example.myapp.databinding.FragmentLocationServiceBinding;
import id.example.myapp.databinding.FragmentTryNotifBinding;

public class LocationServiceFragment extends Fragment {
    FragmentLocationServiceBinding binding;
    private LocationManager locationManager;

    private boolean isTracking = false;


    public LocationServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationServiceBinding.inflate(inflater, container, false);
        // ini lat long binus alsut
        binding.etLat.setText("-6.2236284");
        binding.etLong.setText("106.64921960680044");

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        binding.btnStartTracking.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // req permission
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            } else {
                startLocationUpdates();
            }
        });
        return binding.getRoot();
    }

    private void startLocationUpdates() {
        String latStr = binding.etLat.getText().toString();
        String longStr = binding.etLong.getText().toString();

        if (latStr.isEmpty() || longStr.isEmpty()) {
            Toast.makeText(getContext(), "Isi longitude latitude dulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        final double targetLat = Double.parseDouble(latStr);
        final double targetLong = Double.parseDouble(longStr);

        binding.tvResult.setText("Harap Tunggu...");

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location currentLocation) {
                    // Hitung Jarak di sini setiap user bergerak. ambil dari currentLocation dan target latlong
                    float[] results = new float[1];
                    Location.distanceBetween(
                            currentLocation.getLatitude(), currentLocation.getLongitude(),
                            targetLat, targetLong,
                            results);

                    float distanceInMeters = results[0];

                    // tampilin hasil
                    String info = String.format("Jarak Anda ke Target:\n%.2f Meter\n(%.2f KM)", distanceInMeters, distanceInMeters / 1000);
                    binding.tvResult.setText(info);
                }

                @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
                @Override public void onProviderEnabled(@NonNull String provider) {}
                @Override public void onProviderDisabled(@NonNull String provider) {}
            });
        }
    }

}