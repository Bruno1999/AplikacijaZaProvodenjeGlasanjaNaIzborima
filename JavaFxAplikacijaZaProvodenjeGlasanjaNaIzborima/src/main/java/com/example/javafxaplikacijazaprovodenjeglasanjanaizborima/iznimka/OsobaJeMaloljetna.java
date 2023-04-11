package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.iznimka;

public class OsobaJeMaloljetna extends RuntimeException{

    public OsobaJeMaloljetna() {
    }

    public OsobaJeMaloljetna(String message) {
        super(message);
    }

    public OsobaJeMaloljetna(String message, Throwable cause) {
        super(message, cause);
    }

    public OsobaJeMaloljetna(Throwable cause) {
        super(cause);
    }

    public OsobaJeMaloljetna(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
