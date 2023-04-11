package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

public class Promjene extends Entitet{

    private String staraVrijednost;
    private String novaVrijednost;
    private String osoba;
    private String vrijeme;

    public Promjene(long id, String staraVrijednost, String novaVrijednost, String osoba, String vrijeme) {
        super(id);
        this.staraVrijednost = staraVrijednost;
        this.novaVrijednost = novaVrijednost;
        this.osoba = osoba;
        this.vrijeme = vrijeme;
    }


    public String getStaraVrijednost() {
        return staraVrijednost;
    }

    public void setStaraVrijednost(String staraVrijednost) {
        this.staraVrijednost = staraVrijednost;
    }

    public String getNovaVrijednost() {
        return novaVrijednost;
    }

    public void setNovaVrijednost(String novaVrijednost) {
        this.novaVrijednost = novaVrijednost;
    }

    public String getOsoba() {
        return osoba;
    }

    public void setOsoba(String osoba) {
        this.osoba = osoba;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }
}
