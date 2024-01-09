package com.example.winlabo;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Traitement3 extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Méthode pour initialiser les destinataires
    protected void initDestinataire() {
        // URL pour obtenir la liste des processus depuis un serveur PHP
        String url = "http://mobile.winqual.com/getdestinataire.php";
        // Tâche asynchrone pour récupérer les données du serveur
        ConnectToServer.CheckCredentialsTask task = new ConnectToServer.CheckCredentialsTask(new ConnectToServer.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                try {
                    Gson gson = new Gson();
                    /**
                     * Utilisation de Gson pour désérialiser une chaîne JSON (result) en une liste d'objets de type Destinataire.
                     * Gson analyse la chaîne JSON et la convertit en une liste d'objets Java.
                     */
                    List<Destinataire> listDestinataire = gson.fromJson(result, new TypeToken<List<Destinataire>>() {
                    }.getType());

                    Spinner spinnerDestinataire = findViewById(R.id.spinnerDestinataire);

                    /**
                     Créé un adaptateur pour le Spinner (spinnerDestinataire), le configurant pour afficher
                     les objets de type Destinataire dans une liste déroulante.
                     Enfin, il définit un layout déroulant pour le Spinner et lui assigne l'adaptateur.
                     */
                    ArrayAdapter<Destinataire> adapter = new ArrayAdapter<>(Traitement3.this, android.R.layout.simple_spinner_item, listDestinataire);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDestinataire.setAdapter(adapter);


                    /**
                     On récupère les données partagées avec la clé "session".
                     Ensuite, il extrait un entier avec la clé "idDestinataire" de ces préférences partagées.
                     Si la clé n'existe pas, il renvoie 0 par défaut. Ainsi, cette séquence permet de récupérer
                     un identifiant de destinataire stocké précédemment dans les préférences partagées.
                     */
                    SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                    int idDestinataire = sharedPreferences.getInt("idDestinataire", 0);


                    /**
                     Vérifie si l'idDestinataire récupéré des préférences partagées n'est pas égal
                     à zéro. S'il n'est pas égal à zéro, cela signifie qu'un identifiant de destinataire valide a été stocké
                     précédemment. Ensuite, il parcourt les éléments de l'adaptateur associé au Spinner (spinnerDestinataire) pour
                     trouver l'élément dont l'ID de destinataire correspond à l'idDestinataire récupéré. Une fois trouvé, il définit
                     la sélection du Spinner sur cet élément, assurant ainsi que l'élément correspondant à l'idDestinataire est
                     pré-sélectionné dans la liste déroulante.
                     */
                    if (idDestinataire != 0) {
                        for (int i = 0; i < adapter.getCount(); i++) {
                            Destinataire destinataire = adapter.getItem(i);
                            if (destinataire.getID_LINUX_UTILISAT() == idDestinataire) {
                                spinnerDestinataire.setSelection(i);
                            }
                        }
                    }

                    // Ajout d'un écouteur sur le Spinner pour gérer la sélection
                    spinnerDestinataire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            // Récupération de l'élément sélectionné dans le Spinner
                            Destinataire selectedSite = (Destinataire) parentView.getItemAtPosition(position);

                            // Sauvegardez le nom du processus sélectionné dans les préférences partagées
                            SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            /**
                             Ce code utilise un éditeur de préférences partagées (editor) pour enregistrer l'ID d'un processus
                             sélectionné dans les préférences partagées. L'ID du processus (selectedSite.getID_LINUX_SMQ_PROCESSUS())
                             est stocké avec la clé "idProcessus". La méthode apply() est ensuite appelée pour appliquer ces modifications
                             aux préférences partagées de manière asynchrone. En résumé, cela permet de mémoriser l'ID du processus sélectionné
                             pour une utilisation ultérieure, par exemple, afin de pré-sélectionner le même processus lorsqu'on revient sur cette activité.
                             */
                            editor.putInt("idDestinataire", selectedSite.getID_LINUX_UTILISAT());
                            editor.apply();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // Ne rien faire ici
                        }
                    });

                } catch (Exception e) {
                    Log.d("TAG", "Erreur : " + e.getMessage());
                }

            }
        });
        // Exécution de la tâche asynchrone pour obtenir les données du serveur
        task.execute(url);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        if(item.getItemId() == R.id.profil){
//            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
//            return true;
//        } else
            if (item.getItemId() == R.id.deconnexion) {
            Intent intent = new Intent(Traitement3.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Méthode appelée lors de la création de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement3);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.tr3);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Traitement3.this, Traitement2.class);
        Intent nextIntent = new Intent(Traitement3.this, Enregistrement1.class);
        nextIntent.putExtra("previousActivity", "Traitement3");

        Button previousButton = (Button) findViewById(R.id.Precedent10);

        initDestinataire();
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant11);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });
    }
}