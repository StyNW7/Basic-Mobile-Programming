package id.example.myapp.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import id.example.myapp.databinding.FragmentLocationServiceBinding;
import id.example.myapp.services.LocationMonitorService;

public class LocationServiceFragment extends Fragment {
    FragmentLocationServiceBinding binding;

    public LocationServiceFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationServiceBinding.inflate(inflater, container, false);

        // Coordinate from the question given
        binding.etLat.setText("-6.2236284");
        binding.etLong.setText("106.64921960680044");
        binding.etLat.setEnabled(false);
        binding.etLong.setEnabled(false);
        binding.tvResult.setText("Press button to start the service...");

        binding.btnStartTracking.setText("Start Monitor Service");
        binding.btnStartTracking.setOnClickListener(v -> {
            checkPermissionsAndStartService();
        });

        return binding.getRoot();
    }

    private void checkPermissionsAndStartService() {

        boolean locPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean notifPermission = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notifPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }

        if (locPermission && notifPermission) {
            startMyService();
        } else {
            String[] permissions;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissions = new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.POST_NOTIFICATIONS
                };
            } else {
                permissions = new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };
            }
            ActivityCompat.requestPermissions(requireActivity(), permissions, 101);
        }
    }

    private void startMyService() {
        Intent serviceIntent = new Intent(requireContext(), LocationMonitorService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireContext().startForegroundService(serviceIntent);
        } else {
            requireContext().startService(serviceIntent);
        }

        binding.tvResult.setText("Service Running...");
    }

}