package com.example.winlabo;

public class Categorie {
    private int ID_LINUX_SMQ_PROCESSUS_ACTIVITE;
    private String LIBELLE;

    public int getID_LINUX_SMQ_PROCESSUS_ACTIVITE() {
        return ID_LINUX_SMQ_PROCESSUS_ACTIVITE;
    }

    public void setID_LINUX_SMQ_PROCESSUS_ACTIVITE(int ID_LINUX_SMQ_PROCESSUS_ACTIVITE) {
        this.ID_LINUX_SMQ_PROCESSUS_ACTIVITE = ID_LINUX_SMQ_PROCESSUS_ACTIVITE;
    }

    public String getLIBELLE() {
        return LIBELLE;
    }

    public void setLIBELLE(String LIBELLE) {
        this.LIBELLE = LIBELLE;
    }
    public Categorie(int ID_LINUX_SMQ_PROCESSUS_ACTIVITES, String LIBELLE) {
        this.ID_LINUX_SMQ_PROCESSUS_ACTIVITE = ID_LINUX_SMQ_PROCESSUS_ACTIVITES;
        this.LIBELLE = LIBELLE;
    }
    @Override
    public String toString() {
        // Cette méthode est utilisée par l'ArrayAdapter pour obtenir la représentation en chaîne de l'objet
        return this.getLIBELLE();
    }
}

