package id.example.myapp.fragments;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.example.myapp.databinding.FragmentTryNotifBinding;

public class TryNotifFragment extends Fragment {
    FragmentTryNotifBinding binding;
    private static final String CHANNEL_ID = "channel_trial";



    public TryNotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTryNotifBinding.inflate(inflater, container, false);

        createNotificationChannel(); // Setup channel
        binding.btnNotify.setOnClickListener(v -> checkPermissionAndNotify());

        return binding.getRoot();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "Channel Trial";
            String description = "Channel untuk notifikasi dummy";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void checkPermissionAndNotify() {
        // Hanya minta permission di Android 13 (Tiramisu) ke atas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Cek apakah permission sudah diberikan
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                // kalau permissiion sudah ada, langsung tampilkan notifikasi
                showNotification();
            }
            else{
                // permssion belum ada, minta dlu
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
            }
        } else {
            // Untuk Android 12 ke bawah, tidak perlu izin, langsung tampilkan notifikasi
            showNotification();
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Notifikasi Dummy")
                .setContentText("Ini notif dummy.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

}