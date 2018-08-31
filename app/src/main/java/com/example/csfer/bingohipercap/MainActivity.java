package com.example.csfer.bingohipercap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random randonm;
    private ArrayList<Integer> listaNumerosSorteados;
    private TextView numerosSorteados;
    private TextView numeroSorteado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randonm = new Random(System.currentTimeMillis());
        listaNumerosSorteados = new ArrayList<>();
        numerosSorteados = findViewById(R.id.sorteados);
        numeroSorteado = findViewById(R.id.soteado);


        if(savedInstanceState != null ){
           String historic = savedInstanceState.getString("Historico");
           numerosSorteados.setText(historic);
           String sorteado = savedInstanceState.getString("Sorteado");
           numeroSorteado.setText(sorteado);
           ArrayList<Integer>listaSorteados =  savedInstanceState.getIntegerArrayList("Sorteio");
            listaNumerosSorteados = listaSorteados;
        }
    }

    public void sortear(View view) {
        if (view.getId() == R.id.btnSortear) {
            if (listaNumerosSorteados.size() < 75) {
                Integer numero = randonm.nextInt(75) + 1;
                if (listaNumerosSorteados.contains(numero)) {
                    numero = randonm.nextInt(75) + 1;
                }

                listaNumerosSorteados.add(numero);
                numerosSorteados.setText(numerosSorteados.getText() + numero.toString() + " ");


                String coluna = "";

                if (numero > 1 && numero <= 15) {

                    coluna = "B";
                } else if (numero >= 16 && numero <= 30) {

                    coluna = "I";
                } else if (numero >= 31 && numero <= 45) {

                    coluna = "N";
                } else if (numero >= 46 && numero <= 60) {

                    coluna = "G";
                } else if (numero >= 61 && numero <= 70) {

                    coluna = "O";
                }
                numeroSorteado.setText(coluna + numero.toString());
            } else {

                Toast.makeText(this, "Não possui mais número para serem sorteados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void reiniciar (View view){
        if (view.getId() == R.id.reiniciar) {
            listaNumerosSorteados.clear();
            numeroSorteado.setText("");
            numerosSorteados.setText("");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Historico", numerosSorteados.getText().toString());
        outState.putString("Sorteado", numeroSorteado.getText().toString());
        outState.putIntegerArrayList("Sorteio", listaNumerosSorteados );
        super.onSaveInstanceState(outState);
    }
}

