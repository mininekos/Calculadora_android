package com.example.calculadora.Eventos;

import android.view.View;
import android.widget.Button;

import com.example.calculadora.MainActivity;
import com.example.calculadora.R;
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
        Button b = (Button) view;

        if(b.getText().toString().matches("[0-9]")){
            calculadora.agregarNumero(b.getText().toString());
        }
        if(b.getText().toString().matches("[-+X/]")){
            calculadora.ageragarOperador(b.getText().toString());
        }

        switch(view.getId()) {
            case R.id.btnMC:
                    break;
            case R.id.btnMmas:
                    break;
            case R.id.btnMmenos:
                break;
            case R.id.btnMR:
                break;
            case R.id.btnC:
                break;
            case R.id.btnMasMenos:
                break;
            case R.id.btnIgual:
                break;
            case R.id.btnPunto:
                break;
        }

    }
}
