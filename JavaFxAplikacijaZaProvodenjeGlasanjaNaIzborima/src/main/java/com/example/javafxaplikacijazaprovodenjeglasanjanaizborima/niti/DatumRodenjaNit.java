package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.niti;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Glasac;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.stream.Collectors;

public class DatumRodenjaNit implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from a thread");
        /*var dataSource = VjezbeApplication.getDataSource();
        List<Glasac> imajuRodjendan;

        try {
            imajuRodjendan = dataSource.readStudentiKojiImajuRodjendan();
        } catch (DataSourceException e) {
            e.printStackTrace();
            return;
        }

        if (imajuRodjendan.size() == 0) {
            return;
        }

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rođendan glasaca");
        alert.setHeaderText("Rođendan glasaca:");
        alert.setContentText(imajuRodjendan.stream()
                .map(s -> s.getIme() + " " + s.getPrezime())
                .collect(Collectors.joining("\n"))
        );

        alert.show();*/
    }

}
