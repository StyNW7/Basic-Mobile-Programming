package id.example.myapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import id.example.myapp.fragments.LocationServiceFragment;
import id.example.myapp.fragments.TryNotifFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TryNotifFragment();
            case 1:
                return new LocationServiceFragment();
            default:
                return new TryNotifFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}