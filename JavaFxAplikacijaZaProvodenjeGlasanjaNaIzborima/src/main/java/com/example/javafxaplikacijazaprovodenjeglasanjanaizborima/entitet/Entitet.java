package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

import java.io.Serializable;

public abstract class Entitet implements Serializable {

    private long id;

    public Entitet(long id) {
        this.id = id;
    }

    public Entitet(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
