package com.example.databindinglearn.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databindinglearn.R;
import com.example.databindinglearn.data.SimpleViewModel;
import com.example.databindinglearn.databinding.PlainActivityBinding;

public class PlainOldActivity extends AppCompatActivity {

    private SimpleViewModel viewModel = new SimpleViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.plain_activity);
        binding.setLifecycleOwner(this);

        binding.setViewmodel(viewModel);
    }
}