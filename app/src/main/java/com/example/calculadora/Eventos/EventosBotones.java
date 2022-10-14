package com.example.calculadora.Eventos;

import android.view.View;

import com.example.calculadora.MainActivity;
import com.example.calculadora.databinding.ActivityMainBinding;

public class EventosBotones implements View.OnClickListener {

    private ActivityMainBinding binding;
    private MainActivity calculadora;
    public EventosBotones(MainActivity calculadora){
        this.calculadora=calculadora;
        binding=calculadora.getBinding();

        for (int ind=0; ind<binding.tablita.getChildCount();ind++){
            binding.tablita.getChildAt(ind).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {

    }
}
