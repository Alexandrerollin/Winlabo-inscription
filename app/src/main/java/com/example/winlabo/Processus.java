package com.example.winlabo;

public class Processus {
    private int ID_LINUX_SMQ_PROCESSUS;
    private String TITRE;

    private String CODE;
    public int getID_LINUX_SMQ_PROCESSUS() {
        return ID_LINUX_SMQ_PROCESSUS;
    }

    public void setID_LINUX_SMQ_PROCESSUS(int ID_LINUX_SMQ_PROCESSUS) {
        this.ID_LINUX_SMQ_PROCESSUS = ID_LINUX_SMQ_PROCESSUS;
    }

    public String getTITRE() {
        return TITRE;
    }

    public void setTITRE(String TITRE) {
        this.TITRE = TITRE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }
    public Processus(int ID_LINUX_SMQ_PROCESSUS, String TITRE, String CODE) {
        this.ID_LINUX_SMQ_PROCESSUS = ID_LINUX_SMQ_PROCESSUS;
        this.TITRE = TITRE;
        this.CODE = CODE;
    }

    @Override
    public String toString() {
        // Cette méthode est utilisée par l'ArrayAdapter pour obtenir la représentation en chaîne de l'objet
        return this.getTITRE();
    }
}
