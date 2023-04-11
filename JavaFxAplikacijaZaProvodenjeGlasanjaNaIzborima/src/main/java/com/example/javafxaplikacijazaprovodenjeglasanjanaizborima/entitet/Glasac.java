package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

import java.time.LocalDate;

public class Glasac extends Osoba{

    private String jmbag;

    private LocalDate datumRodenja;

    private String izbornoMjesto;

    private Boolean glasao;


    public Glasac(long id, String ime, String prezime, String jmbag, LocalDate datumRodenja, String izbornoMjesto,Boolean glasao) {
        super(id, ime,prezime);
        this.jmbag = jmbag;
        this.datumRodenja = datumRodenja;
        this.izbornoMjesto = izbornoMjesto;
        this.glasao = glasao;
    }

    public String getIzbornoMjesto() {
        return izbornoMjesto;
    }

    public void setIzbornoMjesto(String izbornoMjesto) {
        this.izbornoMjesto = izbornoMjesto;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public Boolean getGlasao() {
        return glasao;
    }

    public void setGlasao(Boolean glasao) {
        this.glasao = glasao;
    }
}
