package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

public class Stranka extends Entitet{

    private String naziv;
    private Adresa adresa;

    public Stranka(long id, String naziv, Adresa adresa) {
        super(id);
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public Stranka (){

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public static class StrankaBuilder{
        private long id;
        private String naziv;
        private Adresa adresa;

        public StrankaBuilder(long id){this.id = id;}

        public StrankaBuilder setNaziv(String naziv){
            this.naziv = naziv;
            return this;
        }

        public StrankaBuilder setAdresa(Adresa adresa){
            this.adresa = adresa;
            return this;
        }

        public Stranka build(){
            return new Stranka(id,naziv,adresa);
        }

    }

}
