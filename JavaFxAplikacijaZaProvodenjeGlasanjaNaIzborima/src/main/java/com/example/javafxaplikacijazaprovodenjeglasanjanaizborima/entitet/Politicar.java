package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

public class Politicar extends Osoba {

    private Integer sumaGlasova;
    public Politicar(long id, String ime, String prezime,Integer sumaGlasova) {
        super(id, ime,prezime);
        this.sumaGlasova = sumaGlasova;
    }

    public Integer getSumaGlasova() {
        return sumaGlasova;
    }

    public void setSumaGlasova(Integer sumaGlasova) {
        this.sumaGlasova = sumaGlasova;
    }
}
