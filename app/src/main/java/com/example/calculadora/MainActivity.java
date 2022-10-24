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
        cadena= new StringBuilder();
        limpiar();
    }

    //Metodos y lo que hacen los botones

    public ActivityMainBinding getBinding(){return binding;}

    public void agregarNumero(String numero){

        if(estadoNumero==true){
            if(!binding.txtResultado.equals(""))
                binding.txtResultado.setText("");
            if(operacion==false){
                punto=true;
            }
            operacion=true;
            cadena.append(numero);
            binding.txtEscribir.setText(cadena);
        }
    }

    public void ageragarOperador(String operador) {
        if(operacion==true){
            operacion=false;
            punto=false;
            estadoNumero=true;
            if(operando=="")
                operando=operador;
            else{
                calcular();
                operando = operador;
                }
            if(binding.txtResultado.getText().length()==0) {
                cadena.append(operador);
                binding.txtEscribir.setText(cadena);
            }
        }
    }

    public void calcular( ){
        int operador = cadena.indexOf(operando);
        try {
            if((operador!=-1 && operador+1!=cadena.length()) || igual==true) {
                if(igual==false) {
                    num1 = Double.parseDouble(cadena.substring(0, operador));
                    num2 = Double.parseDouble(cadena.substring(operador + 1, cadena.length()));
                }else{
                    if(operando=="")
                        num1 = Double.parseDouble(cadena.substring(0, cadena.length()));
                    else
                        num1 = Double.parseDouble(cadena.substring(0, operador));
                    if(operando!="" && cadena.substring(operador + 1, cadena.length()).length()!=0){
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
                    operando="";
                    cadena.setLength(0);
                    cadena.append(cache);
                    binding.txtEscribir.setText(cadena);

                }
                else {
                    operando="";
                    cadena.setLength(0);
                    cadena.append(num1);
                    binding.txtEscribir.setText(cadena);
                }

            }

            if(cadena.toString().contains("Infinity")) {
                limpiar();
                binding.txtResultado.setText("Division entre cero");
            }
            igual=false;
        }
        catch (Exception e){
            limpiar();
            binding.txtResultado.setText("Error "+e.getMessage());
        }
    }

    public void limpiar() {
        this.cache=0.0;
        cadena.setLength(0);
        binding.txtEscribir.setText("");
        binding.txtResultado.setText("");
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
        if(cadena.length()!=0) {
            igual = true;
            calcular();
            if(binding.txtEscribir.length()!=0)
                operacion=true;
            estadoNumero=false;
        }

    }
}