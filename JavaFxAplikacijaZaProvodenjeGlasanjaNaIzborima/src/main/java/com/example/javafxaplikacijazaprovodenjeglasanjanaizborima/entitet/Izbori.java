package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

import java.math.BigDecimal;
import java.util.List;

public interface Izbori {

    public BigDecimal izracunajIzlaznostNaIzborima(List<Glasac> glasac);

}
