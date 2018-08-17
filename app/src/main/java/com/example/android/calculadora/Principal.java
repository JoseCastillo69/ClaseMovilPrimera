package com.example.android.calculadora;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    //Definicion de variables
    private EditText n1, n2;
    private TextView res;
    private Resources recursos;
    private Spinner operaciones;
    private String op[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        // Conexion de Elementos Layout Principal con variables
        n1 = findViewById(R.id.txtPrimerNumero);
        n2 = findViewById(R.id.txtSegundoNumero);
        res = findViewById(R.id.txtResultado);
        operaciones = findViewById(R.id.cmbOpciones);

        // Guardado de Referencias a los recursos de la aplicacion
        recursos = this.getResources();

        //Conexion con el Array de las opciones
        op = recursos.getStringArray(R.array.opcione);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,op);
        operaciones.setAdapter(adapter);

    }

    // Creacion Metodos (Operaciones)
    public void calcular (View v){
        int opcion;
        double num1, num2, resultado = 0;
        if (validar()){
            opcion = operaciones.getSelectedItemPosition();
            num1 = Double.parseDouble(n1.getText().toString());
            num2 = Double.parseDouble(n2.getText().toString());
            switch (opcion){
                case 1:
                    resultado = Metodos.sumar(num1, num2);
                    break;
                case 2:
                    resultado = Metodos.restar(num1, num2);
                    break;
                case 3:
                    resultado = Metodos.multiplicar(num1, num2);
                    break;
                case 4:
                    resultado = Metodos.dividir(num1, num2);
                    break;
            }
            res.setText(String.format("%.2f",resultado));

        }
        }
    public void limpiar (View v){
        n1.setText("");
        n2.setText("");
        operaciones.setSelection(0);
        n1.requestFocus();
        res.setText("");

    }
    public boolean validar (){
        int o =operaciones.getSelectedItemPosition();
        if (n1.getText().toString().isEmpty()){
            n1.setError(recursos.getString(R.string.error_1));
            n1.requestFocus();
            return false;
        }
        if (n2.getText().toString().isEmpty()){
            n2.setError(recursos.getString(R.string.error_1));
            n2.requestFocus();
            return false;
        }

        if (o == 0){
            Toast.makeText(this,recursos.getString(R.string.error_3),Toast.LENGTH_SHORT).show();
            n1.requestFocus();
            return false;
        }
        if (o == 4 && Double.parseDouble(n2.getText().toString())==0){
            n2.setError(recursos.getString(R.string.error_4));
            n2.requestFocus();
        }
        return true;

    }
}
