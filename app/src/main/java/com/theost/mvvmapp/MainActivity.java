package com.theost.mvvmapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.theost.mvvmapp.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {

    private static final String FLOATING_TAG = "floating_fragment";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.menuButton.setOnClickListener(view -> addFragment(SecondFragment.newInstance()));
        startFragment(FirstFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().findFragmentByTag(FLOATING_TAG) == null) {
            binding.menuButton.setVisibility(VISIBLE);
        }
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    private void addFragment(Fragment fragment) {
        binding.menuButton.setVisibility(GONE);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .addToBackStack(FLOATING_TAG)
                .commit();
    }
}