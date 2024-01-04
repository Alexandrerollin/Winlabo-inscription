package com.example.winlabo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import android.content.SharedPreferences;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeConnecter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_connecter);

        //Action bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_center);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.seco);
        layout.setBackgroundColor(Color.WHITE);

        Intent nextIntent = new Intent(SeConnecter.this, Accueil.class);

        Button nextButton = findViewById(R.id.btnConnexion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = ((EditText) findViewById(R.id.Utilisateur)).getText().toString();
                String motdepasse = ((EditText) findViewById(R.id.MDP)).getText().toString();
                String url = "http://mobile.winqual.com/Connexion.php?pseudo=" + pseudo + "&motdepasse=" + motdepasse;

                ConnectToServer.CheckCredentialsTask task = new ConnectToServer.CheckCredentialsTask(new ConnectToServer.OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(String result) {

                        try {
                            // Utilisation de Gson pour convertir la chaîne JSON en un objet Java
                            Gson gson = new Gson();
                            Utilisateur utilisateur = gson.fromJson(result, Utilisateur.class);

                            Log.d("TAG", "Pseudo: " + utilisateur.getPseudo());

                            SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            // Sauvegarde de l'ID
                            editor.putInt("id", utilisateur.getId());
                            // Sauvegarde du pseudo
                            editor.putString("pseudo", utilisateur.getPseudo());
                            // Sauvegarde du nom
                            editor.putString("nom", utilisateur.getNom());
                            // Sauvegarde du prénom
                            editor.putString("prenom", utilisateur.getPrenom());
                            // Sauvegarde du token
                            editor.putString("token", utilisateur.getToken());
                            // Appliquer les modifications
                            editor.apply();

                            Log.d("TAG", "Token: " + utilisateur.getToken());;
                            Log.d("TAG", "Succes " + utilisateur.getSucces());


                            if ( utilisateur.getSucces() == 1) {
                                // Continuez avec votre logique de redirection ou autre en fonction du résultat
                                Log.d("TAG", "Authentication réussie");
                                startActivity(nextIntent);
                            }else {
                                Toast.makeText(SeConnecter.this, utilisateur.getRaison(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("TAG", "Erreur aaa: " + e.getMessage());
                        }

                    }
                });

//                task.execute(pseudo, motdepasse);
                task.execute(url);
            }
        });
    }
}