package com.example.myapplication.Controller.HistoriqueFrag;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Model.AccesLocal;
import com.example.myapplication.Model.HistoryMatch;
import com.example.myapplication.R;

public class HistoriqueFragment extends Fragment {
    private static AccesLocal accesLocal;
    private static HistoryMatch historyMatch;
    private EditText Name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recupMatch();

        //Name = getView().findViewById(R.id.textViewJ1Name1);
        recupMatch();
        return inflater.inflate(R.layout.fragment_historique,null);
    }



//Récupère ce qu'il y a sur la bddLite
    private void recupMatch(){

        accesLocal = new AccesLocal(this.getContext());
        historyMatch =  accesLocal.recupDernier();
        Log.d("HISTOTRUC",historyMatch.getJoueur1LastName());
        //Name.setText("test");
    }




}
