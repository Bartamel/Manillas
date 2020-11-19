package com.example.manillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText cant;
    private TextView resultado;
    private Spinner combomat, combodije, combotip,combomon;
    private String opcionmat[],opciondije[], opciontip[], opcionmon[];
    private ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Captura de los objetos
        cant=findViewById(R.id.lblcantidad);
        resultado=findViewById(R.id.lblResultado);
        combomat=findViewById(R.id.cmbMaterial);
        combodije=findViewById(R.id.cmbDije);
        combomon=findViewById(R.id.cmbMoneda);
        combotip=findViewById(R.id.cmbTipo);

        //Traemos las operaciones de los array de String
        opcionmat=getResources().getStringArray(R.array.materiales);
        adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opcionmat);
        combomat.setAdapter(adapter1);
        opciondije=getResources().getStringArray(R.array.dije);
        adapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opciondije);
        combodije.setAdapter(adapter2);
        opciontip=getResources().getStringArray(R.array.tipo);
        adapter3=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opciontip);
        combotip.setAdapter(adapter3);
        opcionmon=getResources().getStringArray(R.array.moneda);
        adapter4=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opcionmon);
        combomon.setAdapter(adapter4);
    }
    public void calcular(View v){
        int canti,mate,dij,tipo,mone;
        double resul=0,total=0;
        if(validar()){ mate=combomat.getSelectedItemPosition();
            dij=combodije.getSelectedItemPosition();
            tipo=combotip.getSelectedItemPosition();
            mone=combomon.getSelectedItemPosition();
            canti=Integer.parseInt(cant.getText().toString());
            if(mate==0){//Material: Cuero
                if(dij==0){//Dije: Martillo
                    if(tipo==0 || tipo==1){ //Tipo: Oro o Oro Rosado
                        resul=100;
                    }if(tipo==2){//Tipo: Plata
                        resul=80;
                    }if(tipo==3){//Tipo: Niquel
                        resul=70;
                    }}
                if(dij==1){//Ancla
                    if(tipo==0 || tipo==1){//Tipo: Oro o Oro Rosado
                        resul=120;
                    }if(tipo==2){//Tipo: Plata
                        resul=100;
                    }if(tipo==3){//Tipo: Niquel
                        resul=90;
                    }}
            }if(mate==1){//Material: Cuerda
                if(dij==0){//Dije: Martillo
                    if(tipo==0 || tipo==1){ //Tipo: Oro o Oro Rosado
                        resul=90;
                    }if(tipo==2){//Tipo: Plata
                        resul=70;
                    }if(tipo==3){//Tipo: Niquel
                        resul=50;
                    }}
                if(dij==1){//Ancla
                    if(tipo==0 || tipo==1){//Tipo: Oro o Oro Rosado
                        resul=110;
                    }if(tipo==2){//Tipo: Plata
                        resul=90;
                    }if(tipo==3){//Tipo: Niquel
                        resul=80;
                    }}}
            total=resul*canti;
            if(mone==1){
                double resul1=total*3200;//conversion de divisa
                resultado.setText("COP $"+resul);
            }else{
                resultado.setText("US $"+total);}}

    }

    public boolean validar(){
        if(cant.getText().toString().isEmpty()){
            cant.setError(getString(R.string.mensaje_error_cantidad));
            cant.requestFocus();
            return false;
        }
        if(0>=Integer.parseInt(cant.getText().toString())){
            cant.setError(getString(R.string.mensaje_error_cantida_negativa));
            cant.requestFocus();
            return false;
        }
        return true;
    }
}