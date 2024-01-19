package com.example.winlabo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Enregistrement1 extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        if(item.getItemId() == R.id.profil){
//            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
//            return true;
//        } else
        if (item.getItemId() == R.id.deconnexion) {
            Intent intent = new Intent(Enregistrement1.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_1);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.enr1);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Enregistrement1.this, Traitement3.class);
        Intent PreviousIntent2 = new Intent(Enregistrement1.this, Derogation1.class);
        Intent NextIntent = new Intent(Enregistrement1.this, Enregistrement2.class);

        String previousActivity = getIntent().getStringExtra("previousActivity");

        Button previousButton = (Button) findViewById(R.id.Precedent32);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "valeur previous activity: " + previousActivity);
                if ("Traitement3".equals(previousActivity)) {
                    startActivity(previousIntent);
                } else if ("Derogation1".equals(previousActivity)) {
                    startActivity(PreviousIntent2);
                }
            }
        });

        Button validateButton = (Button) findViewById(R.id.Suivant32);
        validateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Récupérer les préférences partagées
                SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                // Récupérer les données
                String declarationEvenement1 = sharedPreferences.getString("nomLaboratoire", "default_value");
                int declarationEvenement2 = sharedPreferences.getInt("idProcessus", 0);
                int declarationEvenement3 = sharedPreferences.getInt("idCategorie", 0);
                String declarationEvenement4 = sharedPreferences.getString("LeDescriptif", "default_value");
                String declarationEvenement5 = sharedPreferences.getString("RadioCritique", "default_value");
//                String declarationEvenement6 = sharedPreferences.getString("edit_text_key", "default_value");
                String Traitement1 = sharedPreferences.getString("Causes", "default_value");
                String Traitement2 = sharedPreferences.getString("Impact", "default_value");
                int Traitement3 = sharedPreferences.getInt("idDestinataire", 0);
                String derogation1 = sharedPreferences.getString("LaDerogation", "default_value");

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("nomDuLaboratoire", declarationEvenement1)
                        .add("idDuProcessus", String.valueOf(declarationEvenement2))
                        .add("idDeCategorie", String.valueOf(declarationEvenement3))
                        .add("leDescriptif", declarationEvenement4)
                        .add("selectionRadio", declarationEvenement5)
//                        .add("nomDuLaboratoire", declarationEvenement6)
                        .add("LesCauses", Traitement1)
                        .add("Limpact", Traitement2)
                        .add("idDuDestinataire", String.valueOf(Traitement3))
                        .add("laDerogation", derogation1)
                        .build();


                Request request = new Request.Builder()
                        .url("http://mobile.winqual.com/Validation.php")
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            // Handle the response
                            String responseData = response.body().string();
                            Log.d(TAG, "ResponseData : " + responseData);
                            boolean estUnEi = responseData.contains("EI-");
                            if (estUnEi) {
                                Log.d(TAG, "ça marche la ref est : " + responseData);
                                NextIntent.putExtra("ref", responseData);
                                NextIntent.putExtra("erreur", "");
                            } else {
                                Log.d(TAG, "Aiii l'erreur est : " + responseData);
                                NextIntent.putExtra("ref", "");
                                NextIntent.putExtra("erreur", responseData);
                            }
                            // tester les 2 premiers characteres, si == EI- c'est ok sinon pb

                            // Démarrer l'activité Enregistrement2
                            startActivity(NextIntent);
                        }
                    }
                });

            }
        });
    }
}


