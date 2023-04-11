package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

import java.util.List;

public class PolitickeStranke<T extends Izbori> extends Stranka {

    List<T> listStranaka;

    public PolitickeStranke(long id, String naziv, Adresa adresa, List<T> listStranaka) {
        super(id, naziv, adresa);
        this.listStranaka = listStranaka;
    }

    public PolitickeStranke(List<T> listStranaka){this.listStranaka=listStranaka;}

    public PolitickeStranke(){}

    public List<T> getListStranaka(){return listStranaka;}

    public void setListStranaka(){this.listStranaka=listStranaka;}

}
