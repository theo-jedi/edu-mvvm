package com.theost.mvvmapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.theost.mvvmapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FirstViewModel viewModel;
    private FragmentFirstBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);
        binding = FragmentFirstBinding.inflate(getLayoutInflater());

        viewModel.loadingStatus.observe(getViewLifecycleOwner(), status -> {
            switch (status) {
                case LOADING:
                    showLoading();
                    break;
                case SUCCESS:
                    hideLoading();
                    break;
                case ERROR:
                    hideLoading();
                    showError();
                    break;
            }
        });

        viewModel.number.observe(getViewLifecycleOwner(), number -> {
            binding.numberView.setText(number.toString());
        });

        binding.reloadButton.setOnClickListener(view -> viewModel.loadData());

        return binding.getRoot();
    }

    private void showLoading() {
        binding.reloadButton.setVisibility(View.GONE);
        binding.numberView.setVisibility(View.GONE);
        binding.loadingBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.reloadButton.setVisibility(View.VISIBLE);
        binding.numberView.setVisibility(View.VISIBLE);
        binding.loadingBar.setVisibility(View.GONE);
    }

    private void showError() {
        Toast.makeText(getContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
    }

    public static Fragment newInstance() {
        return new FirstFragment();
    }
}
