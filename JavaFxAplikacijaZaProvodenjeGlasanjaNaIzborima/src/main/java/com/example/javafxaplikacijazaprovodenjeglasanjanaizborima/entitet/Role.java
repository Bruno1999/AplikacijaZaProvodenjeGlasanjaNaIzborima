package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

public enum Role {

    PRAZNO(0,"prazno"),
    ADMIN(1,"admin"),
    USER(2,"user"),
    MODERATOR(3,"moderator");

    private final Integer numerickaVrijednost;

    private final String opis;

    Role(Integer numerickaVrijednost, String opis) {
        this.numerickaVrijednost = numerickaVrijednost;
        this.opis = opis;
    }

    public Integer getNumerickaVrijednost() {
        return numerickaVrijednost;
    }

    public String getOpis() {
        return opis;
    }

}
