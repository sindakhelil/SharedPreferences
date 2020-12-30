package com.dsi32.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //
    private SharedPreferences Preferences;
    private SharedPreferences.Editor editor;
    private EditText user,password;
    private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.id_name);
        password = findViewById(R.id.id_pass);
        Button btnlogin = findViewById(R.id.button_log);
        checkbox = findViewById(R.id.checkbox);
        //Initialisation Préférences partagés
        //Stocker des données
        Preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // nous avons besoin d'un editor objet pour faire le changement
        editor = Preferences.edit();
        //appel de la méthode
        CheckSharedPreferences();
        btnlogin.setOnClickListener(v -> {
            //enregistrer la préférence de case à cocher
            if(checkbox.isChecked()){
                // cocher une case au démarrage de l'application
                editor.putString(getString(R.string.checkboxLog),"true");
                // Commit le changement
                editor.apply();
                //enregistrer nom
                String nom = user.getText().toString();
                editor.putString(getString(R.string.nom),nom);
                // Commit le changement
                editor.commit();
                //enregistrer mot de passse
                String pass = password.getText().toString();
                editor.putString(getString(R.string.pass),pass);
                // Commit le changement
                editor.commit();
            }else{
                //les mêmes étapes dans le cas contraire
                editor.putString(getString(R.string.checkboxLog),"false");
                // Commit le changement
                editor.commit();
                editor.putString(getString(R.string.nom),"");
                // Commit le changement
                editor.commit();
                editor.putString(getString(R.string.pass),"");
                // Commit le changement
                editor.commit();
            }
        });
    }
    //cvérifiez les préférences partagées et définissez-les
    private void CheckSharedPreferences(){
        String nom = Preferences.getString(getString(R.string.nom),"");
        String pass = Preferences.getString(getString(R.string.pass),"");
        String checkboxLog = Preferences.getString(getString(R.string.checkboxLog),"False");
        user.setText(nom);
        password.setText(pass);
        checkbox.setChecked(checkboxLog.equals("true"));

    }
}