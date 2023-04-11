package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.iznimka;

public class OsobaJeGlasala  extends  Exception{

    public OsobaJeGlasala() {
    }

    public OsobaJeGlasala(String message) {
        super(message);
    }

    public OsobaJeGlasala(String message, Throwable cause) {
        super(message, cause);
    }

    public OsobaJeGlasala(Throwable cause) {
        super(cause);
    }

    public OsobaJeGlasala(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
