package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Politicar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UnosPoliticaraController implements Initializable {

    @FXML
    private TextField imeTextField;
    @FXML
    private TextField prezimeTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    public void spremiPoliticara() {

        StringBuilder errorMessages = new StringBuilder();

        long id = 0L;

        Connection connectionID;
        {
            try {
                connectionID = BazaPodataka.connectToDatabase();
                List<Politicar> politicarList = BazaPodataka.getAllPoliticarFromDatabase(connectionID);
                id = (long) (politicarList.size() + 1);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }


        String ime = imeTextField.getText();

        if(ime.isEmpty()) {
            errorMessages.append("name should not be empty!\n");
        }

        String prezime = prezimeTextField.getText();

        if(prezime.isEmpty()) {
            errorMessages.append("Last name should not be empty!\n");
        }


        if(errorMessages.isEmpty()) {
            Politicar newPoliticar = new Politicar(id + 1, ime,prezime,0);


            Connection connection;
            {
                try {
                    connection = BazaPodataka.connectToDatabase();
                    BazaPodataka.insertNewPoliticarToDatabase(newPoliticar);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save action succeded!");
            alert.setHeaderText("Politicar data saved!");
            alert.setContentText("Politicar " + ime + " "  + " saved to the database!");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save action failed!");
            alert.setHeaderText("Politicar data not saved!");
            alert.setContentText(errorMessages.toString());

            alert.showAndWait();
        }

    }
}
