package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.iznimka;

public class PostojiViseStranakaIstogNaziva extends RuntimeException{

    public PostojiViseStranakaIstogNaziva() {
    }

    public PostojiViseStranakaIstogNaziva(String message) {
        super(message);
    }

    public PostojiViseStranakaIstogNaziva(String message, Throwable cause) {
        super(message, cause);
    }

    public PostojiViseStranakaIstogNaziva(Throwable cause) {
        super(cause);
    }

    public PostojiViseStranakaIstogNaziva(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
