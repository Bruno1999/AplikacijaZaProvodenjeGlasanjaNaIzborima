package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Glasac;
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

public class UnosGlasacController implements Initializable {


    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    @FXML
    private TextField imeGlasacaTextField;
    @FXML
    private TextField prezimeGlasacaTextField;
    @FXML
    private TextField jmbagGlasacaTextField;
    /*@FXML
    private TextField idStudentTextField;*/
    @FXML
    private DatePicker datumRodenjaDatePicker;

    @FXML
    private TextField izbornoMjestoTextField;
    @FXML
    private TextField glasaoTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    public void spremiGlasaca() {

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
                List<Glasac> glasacList = BazaPodataka.getAllGlasacFromDatabase(connectionID);
                id = (long) (glasacList.size() + 1);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }


        String ime = imeGlasacaTextField.getText();

        if(ime.isEmpty()) {
            errorMessages.append("First name should not be empty!\n");
        }

        String prezime = prezimeGlasacaTextField.getText();

        if(prezime.isEmpty()) {
            errorMessages.append("Last name should not be empty!\n");
        }

        String jmbag = jmbagGlasacaTextField.getText();

        if(jmbag.isEmpty()) {
            errorMessages.append("JMBAG should not be empty!\n");
        }


        LocalDate datumRodenja = datumRodenjaDatePicker.getValue();

        if(Optional.ofNullable(datumRodenja).isPresent() == false) {
            errorMessages.append("Date of birth should not be empty!\n");
        }

        String izbornoMjesto = izbornoMjestoTextField.getText();

        if(izbornoMjesto.isEmpty()) {
            errorMessages.append("Izborno mjesto should not be empty!\n");
        }
        String glasao = glasaoTextField.getText();

        if(glasao.isEmpty()) {
            errorMessages.append("JMBAG should not be empty!\n");
        }


        Boolean glasaoBool = Boolean.parseBoolean(glasao);

        if(errorMessages.isEmpty()) {
            Glasac newGlasac = new Glasac(id + 1, ime,prezime,jmbag,datumRodenja,izbornoMjesto,glasaoBool);

            /*List<Student> studentList = null;
            try {
                studentList = getStudentList();

                studentList.add(newStudent);

                saveStudents(studentList);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            Connection connection;
            {
                try {
                    connection = BazaPodataka.connectToDatabase();
                    BazaPodataka.insertNewGlasacToDatabase(newGlasac);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save action succeded!");
            alert.setHeaderText("Glasac data saved!");
            alert.setContentText("Glasac " + ime + " " + prezime + " saved to the database!");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save action failed!");
            alert.setHeaderText("Student data not saved!");
            alert.setContentText(errorMessages.toString());

            alert.showAndWait();
        }

    }
}
