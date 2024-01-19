package com.example.winlabo;

public class Destinataire {
    private int ID_LINUX_UTILISAT;
    private String NOM;
    private String CODE;

    public int getID_LINUX_UTILISAT() {
        return ID_LINUX_UTILISAT;
    }

    public void setID_LINUX_UTILISAT(int ID_LINUX_UTILISAT) {
        this.ID_LINUX_UTILISAT = ID_LINUX_UTILISAT;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public Destinataire(int ID_LINUX_UTILISAT, String NOM, String CODE) {
        this.ID_LINUX_UTILISAT = ID_LINUX_UTILISAT;
        this.NOM = NOM;
        this.CODE = CODE;
    }

    @Override
    public String toString() {
        return this.getNOM();
    }
}
