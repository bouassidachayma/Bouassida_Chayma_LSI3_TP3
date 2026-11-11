package com.payassistai.bouassida_chayma_lsi3_TP3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnConsulter ;
    private SeekBar sbAge;
    private RadioButton rboui,rbnon;
    private EditText etValeur;
    private TextView textAge;
    private TextView msg;
    private int age = 0;
    private void init(){
        btnConsulter = findViewById(R.id.btnConsulter);
        sbAge = findViewById(R.id.sbAge);
        rboui = findViewById(R.id.rbtOui);
        rbnon = findViewById(R.id.rbnon);
        etValeur = findViewById(R.id.etValeur);
        textAge = findViewById(R.id.textAge);
        msg = findViewById(R.id.msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // attache un écouteur (listener) au composant SeekBar nommé sbAge.
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //envoie un message dans la console de débogage ( Logcat ) d'Android Studio
                //Log.i → "i" signifie information , c'est un type de log.
                //"Information" → c'est la balise (tag) pour identifier la source du message.
                //"onProgressChanged" + progress → c'est le message qui sera affiché
                Log.i("Information","onProgressChanged" + progress);
                age = progress;
                textAge.setText("Votre age:" + progress + "ans");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulterGlycemie();
            }
        });
    }

//     public void calculer(View view) {
//        // Logique à exécuter lors du clic sur le bouton
//        // Remplacez par le code que vous souhaitez exécuter.
//        // Par exemple, afficher un message d'information.
//        Toast.makeText(this,"Le bouton Consulter a été cliqué",Toast.LENGTH_SHORT).show();
//     }
    private void consulterGlycemie(){
        String valeurString = etValeur.getText().toString().trim();
        if (valeurString.isEmpty()){
            Toast.makeText(this,"Veuillez entrer la valeur mesurée",Toast.LENGTH_SHORT).show();
            return;
        }
        double valeur = Double.parseDouble(valeurString);
        boolean aJeune = rboui.isChecked();
        String resultat;

        double min, max;

        // Détermination des intervalles normaux
        if (aJeune) {

            if (age > 13) {
                min = 5.0;
                max = 7.2;
            } else if (age >= 6 ) {
                min = 5.0;
                max = 10.0;
            } else { // moins de 6 ans
                min = 5.5;
                max = 10.0;
            }

        } else {
            min = 0.0;
            max = 10.5;
        }

        // Vérification du niveau de glycémie
        if (valeur < min){
            resultat = "Le niveau de glycémie est trop bas";
        } else if (valeur > max){
            resultat = "Le niveau de glycémie est trop élevé";
        } else {
            resultat = "le niveau de glycémie est normal";
        }
        msg.setText(resultat);
    }
}