package com.example.winlabo;

public class Site {
    private int ID_LABORATOIRE;
    private String CODE;
    private String NOM_DU_LABORATOIRE;

    public int getID_LABORATOIRE() {
        return ID_LABORATOIRE;
    }

    public void setID_LABORATOIRE(int ID_LABORATOIRE) {
        this.ID_LABORATOIRE = ID_LABORATOIRE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNOM_DU_LABORATOIRE() {
        return NOM_DU_LABORATOIRE;
    }

    public void setNOM_DU_LABORATOIRE(String NOM_DU_LABORATOIRE) {
        this.NOM_DU_LABORATOIRE = NOM_DU_LABORATOIRE;
    }

    public Site(int ID_LABORATOIRE, String CODE, String NOM_DU_LABORATOIRE) {
        this.ID_LABORATOIRE = ID_LABORATOIRE;
        this.CODE = CODE;
        this.NOM_DU_LABORATOIRE = NOM_DU_LABORATOIRE;
    }

}
