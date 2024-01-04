package com.example.winlabo;

        import android.content.Intent;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.Scanner;

public class Inscription extends AppCompatActivity {

    private Button btn_send;
    private EditText et_pseudo, et_email, et_password, et_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btn_send = findViewById(R.id.Inscription);
        et_pseudo = findViewById(R.id.Utilisateur);
        et_email = findViewById(R.id.Email);
        et_password = findViewById(R.id.MDP1);
        et_password2 = findViewById(R.id.MDP2);

        //Action bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_center);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gérez le clic sur le bouton d'inscription
                enregistrerCompte();
                Log.d("TAG", "EnregistrerCompte");
            }
        });
    }

    private void enregistrerCompte() {
        String pseudo = et_pseudo.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String password2 = et_password2.getText().toString().trim();

        // Vérifier si les champs sont vides
        if (pseudo.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            Toast.makeText(Inscription.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier si les mots de passe correspondent
        if (!password.equals(password2)) {
            Toast.makeText(Inscription.this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Effectuer la demande d'inscription
        performInscription(pseudo, email, password, password2);
        Log.d("TAG", "DemandeInscription");
    }

    private void performInscription(final String pseudo, final String email, final String password, final String password2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // URL de votre fichier inscription.php
                    URL url = new URL("http://mobile.winqual.com/inscription.php?pseudo=" + pseudo + "&email=" + email + "&motdepasse=" + password + "&motdepasse_confirmation=" + password2);


                    // Ouvrir une connexion HTTP
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Construire les paramètres de la demande
                    String params = "pseudo=" + pseudo + "&email=" + email + "&motdepasse=" + password;
                    byte[] postData = params.getBytes();

                    // Écrire les paramètres dans la sortie de la connexion
                    OutputStream os = conn.getOutputStream();
                    os.write(postData);

                    // Lire la réponse du serveur
                    Scanner scanner = new Scanner(conn.getInputStream());
                    final StringBuilder response = new StringBuilder();
                    while (scanner.hasNext()) {
                        response.append(scanner.next());
                    }

                    // Fermer les flux
                    scanner.close();
                    os.close();

                    // Traiter la réponse dans le thread principal
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            handleInscriptionResponse(response.toString());
                        }
                    });

                    // Vérifier si les mots de passe correspondent
                    if (!password.equals(password2)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Inscription.this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    } else if (password.length() < 8) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(Inscription.this, "Le mot de passe doit contenir au moins 8 caractères.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleInscriptionResponse(String response) {
        // Traiter la réponse du serveur ici
        // Vous pouvez afficher un Toast ou rediriger vers une autre activité en fonction de la réponse
        Log.d("TAG", "response : " + response);;

        if (response.equals("1")) {
            // Cas de succès
            Toast.makeText(Inscription.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
            Intent previousIntent = new Intent(Inscription.this, MainActivity.class);
            startActivity(previousIntent);
            finish(); // Fermer l'activité d'inscription pour éviter de revenir en arrière avec le bouton de retour
        } else if (response.equals("5")) {
            // Le nom d'utilisateur doit contenir au moins 5 caractères
            Toast.makeText(Inscription.this, "Le nom d'utilisateur doit contenir au moins 5 caractères.", Toast.LENGTH_SHORT).show();
        } else if (response.equals("0")) {
            // Email incorrect
            Toast.makeText(Inscription.this, "Email incorrect", Toast.LENGTH_SHORT).show();
        } else if (response.equals("4")) {
            // Le mot de passe doit contenir au moins 8 caractères
            Toast.makeText(Inscription.this, "Le mot de passe doit contenir au moins 8 caractères.", Toast.LENGTH_SHORT).show();
        } else if (response.equals("2")) {
            // Paramètres manquants
            Toast.makeText(Inscription.this, "Paramètres manquants", Toast.LENGTH_SHORT).show();
        } else if (response.equals("3")) {
            // Les mots de passe sont différents
            Toast.makeText(Inscription.this, "Les mots de passe sont différents", Toast.LENGTH_SHORT).show();
        } else {
            // En dernier recours, afficher "Une erreur est survenue"
            Toast.makeText(Inscription.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
        }
    }
}