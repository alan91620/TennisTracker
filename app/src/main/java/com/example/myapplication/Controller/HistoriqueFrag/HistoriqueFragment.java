package com.example.myapplication.Controller.HistoriqueFrag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Model.AccesLocal;
import com.example.myapplication.R;

public class HistoriqueFragment extends Fragment {
    private static AccesLocal accesLocal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recupMatch();

        return inflater.inflate(R.layout.fragment_historique,null);
    }


    private void recupMatch(){




    }


}
