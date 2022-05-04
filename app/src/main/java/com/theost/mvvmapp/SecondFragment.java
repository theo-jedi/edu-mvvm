package com.theost.mvvmapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theost.mvvmapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(getLayoutInflater());
        binding.toolbar.setNavigationOnClickListener(view -> requireActivity().onBackPressed());
        return binding.getRoot();
    }

    public static Fragment newInstance() {
        return new SecondFragment();
    }
}
