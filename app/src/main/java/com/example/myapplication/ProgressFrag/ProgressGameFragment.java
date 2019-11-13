package com.example.myapplication.ProgressFrag;

import android.content.Context;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;


public class ProgressGameFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_progress,null);
    }


}

