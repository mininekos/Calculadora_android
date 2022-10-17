package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.calculadora.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //Variables que necesite
    Double num;
    StringBuilder cadena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Declaracion de variables


    }

    //Metodos y lo que hacen los botones

    public ActivityMainBinding getBinding(){return binding;}

    public void agregarNumero(String numero){
        cadena.append(numero);
    }

    public void ageragarOperador(String operador) {
        if(cadena.lastIndexOf("+")==cadena.length()-1)
        cadena.append(operador);
    }

    public void calcular(){
        Double num1, num2;
        StringBuilder cadenaOperacion=cadena;
        Boolean error=false;

    }
}