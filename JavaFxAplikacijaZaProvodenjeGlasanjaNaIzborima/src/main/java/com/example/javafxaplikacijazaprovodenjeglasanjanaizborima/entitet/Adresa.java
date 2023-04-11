package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

import java.io.Serializable;

public record Adresa(String adresa) implements Serializable {

    public Adresa(String adresa) {
        this.adresa = adresa;
    }

}
