package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.calculadora.Eventos.EventosBotones;
import com.example.calculadora.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //Variables que necesite
    private Double num1,num2, cache;
    private StringBuilder cadena;
    private Boolean operacion, estadoNumero, punto, igual;
    private String operando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        new EventosBotones(this);
        //Declaracion de variables
        limpiar();
    }

    //Metodos y lo que hacen los botones

    public ActivityMainBinding getBinding(){return binding;}

    public void agregarNumero(String numero){
        if(operacion==false){
            punto=true;
        }
        if(estadoNumero==true){
            operacion=true;
            cadena.append(numero);
            binding.txtEscribir.setText(cadena);
        }
    }

    public void ageragarOperador(String operador) {
        if(operacion==true){
            operacion=false;
            punto=false;
            if(operando=="")
                operando=operador;
            else{
                calcular();
                operando = operador;
                }
            cadena.append(operador);
            binding.txtEscribir.setText(cadena);
        }
    }

    public void calcular( ){
        int operador = cadena.indexOf(operando);
        try {
            if(operador!=-1) {
                if(igual==false) {
                    num1 = Double.parseDouble(cadena.substring(0, operador));
                    num2 = Double.parseDouble(cadena.substring(operador + 1, cadena.length()));
                }else{
                    num1 = Double.parseDouble(cadena.substring(0, operador));
                    if(cadena.substring(operador + 1, cadena.length()).length()!=0){
                        num2 = Double.parseDouble(cadena.substring(operador + 1, cadena.length()));
                    }
                    else
                        num2=null;
                }
                if(num2!=null) {
                    switch (operando) {
                        case "+":
                            cache = num1 + num2;
                            break;
                        case "-":
                            cache = num1 - num2;
                            break;
                        case "X":
                            cache = num1 * num2;
                            break;
                        case "/":
                            cache = num1 / num2;
                            break;
                    }
                    cadena.setLength(0);
                    cadena.append(cache);
                    binding.txtEscribir.setText(cadena);
                }
                else{
                    cadena.setLength(0);
                    cadena.append(num1);
                    binding.txtEscribir.setText(cadena);
                }

            }
            else{
                if(igual==true && cadena.length()!=0){
                    cache = Double.parseDouble(cadena.substring(0, cadena.length()-1));
                    cadena.append(cache);
                    binding.txtEscribir.setText(cadena);
                }
            }
            if(cadena.toString().contains("Infinity")){
                limpiar();
                binding.txtEscribir.setText("Division entre cero");
            }
        }
        catch (Exception e){
            limpiar();
            binding.txtEscribir.setText("Error");

        }
    }

    public void limpiar() {
        this.cache=0.0;
        cadena = new StringBuilder();
        binding.txtEscribir.setText("");
        operacion=false;
        estadoNumero=true;
        punto=false;
        operando="";
        igual=false;
        num1=0.0;
        num2=0.0;
    }

    public void agregarPunto() {
        if (cadena.length()!=0 && punto==true){
            punto=false;
            cadena.append(".");
            binding.txtEscribir.setText(cadena);
        }

    }

    public void hacerCalculo() {
        igual=true;
        calcular();
    }
}