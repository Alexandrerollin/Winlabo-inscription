package com.example.winlabo;

public class Categorie {
    private int ID_LINUX_SMQ_PROCESSUS_ACTIVITES;
    private String LIBELLE;

    public int getID_LINUX_SMQ_PROCESSUS_ACTIVITES() {
        return ID_LINUX_SMQ_PROCESSUS_ACTIVITES;
    }

    public void setID_LINUX_SMQ_PROCESSUS_ACTIVITES(int ID_LINUX_SMQ_PROCESSUS_ACTIVITES) {
        this.ID_LINUX_SMQ_PROCESSUS_ACTIVITES = ID_LINUX_SMQ_PROCESSUS_ACTIVITES;
    }

    public String getLIBELLE() {
        return LIBELLE;
    }

    public void setLIBELLE(String LIBELLE) {
        this.LIBELLE = LIBELLE;
    }
    public Categorie(int ID_LINUX_SMQ_PROCESSUS_ACTIVITES, String LIBELLE) {
        this.ID_LINUX_SMQ_PROCESSUS_ACTIVITES = ID_LINUX_SMQ_PROCESSUS_ACTIVITES;
        this.LIBELLE = LIBELLE;
    }
    @Override
    public String toString() {
        // Cette méthode est utilisée par l'ArrayAdapter pour obtenir la représentation en chaîne de l'objet
        return this.getLIBELLE();
    }
}

