package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Adresa;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Glasac;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Stranka;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UnosStranakaController implements Initializable {

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField adresaTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    public void spremiStranka() {

        StringBuilder errorMessages = new StringBuilder();

        /*String id = idStudentTextField.getText();

        if(id.isEmpty()) {
            errorMessages.append("ID should not be empty!\n");
        }

        long idLong = Long.parseLong(id);*/

        long id = 0L;

        Connection connectionID;
        {
            try {
                connectionID = BazaPodataka.connectToDatabase();
                List<Stranka> strankaList = BazaPodataka.getAllStrankaFromDatabase(connectionID);
                id = (long) (strankaList.size() + 1);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }


        String naziv = nazivTextField.getText();

        if(naziv.isEmpty()) {
            errorMessages.append("name should not be empty!\n");
        }

        String adresaString = adresaTextField.getText();

        if(adresaString.isEmpty()) {
            errorMessages.append("Adresa should not be empty!\n");
        }


        Adresa adresa = new Adresa(adresaString);

        if(errorMessages.isEmpty()) {
            Stranka newStranka = new Stranka(id + 1, naziv,adresa);


            Connection connection;
            {
                try {
                    connection = BazaPodataka.connectToDatabase();
                    BazaPodataka.insertNewStrankaToDatabase(newStranka);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save action succeded!");
            alert.setHeaderText("Glasac data saved!");
            alert.setContentText("Stranka " + naziv + " "  + " saved to the database!");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save action failed!");
            alert.setHeaderText("Stranka data not saved!");
            alert.setContentText(errorMessages.toString());

            alert.showAndWait();
        }

    }
}
