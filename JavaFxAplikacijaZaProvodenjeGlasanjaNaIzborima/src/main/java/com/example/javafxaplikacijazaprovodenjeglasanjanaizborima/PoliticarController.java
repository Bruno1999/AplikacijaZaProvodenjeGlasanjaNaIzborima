package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Politicar;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Stranka;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PoliticarController implements Initializable {


    private static Connection connection;
    private static List<Politicar> politicarList = new ArrayList<>();
    static {
        try {
            connection = BazaPodataka.connectToDatabase();
            politicarList = BazaPodataka.getAllPoliticarFromDatabase(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Politicar> observableListPoliticar;


    @FXML
    private TextField imePoliticaraTextField;
    @FXML
    private TextField prezimePoliticaraTextField;

    @FXML
    private TableView<Politicar> politicarTableView;
    @FXML
    private TableColumn<Politicar,String> imePoliticaraTableColumn;
    @FXML
    private TableColumn<Politicar,String> prezimePoliticaraTableColumn;


    public void initialize(URL url, ResourceBundle resourceBundle){

        imePoliticaraTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getIme()));
        prezimePoliticaraTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getPrezime()));

        if (observableListPoliticar == null){
            observableListPoliticar = FXCollections.observableArrayList();
        }

        observableListPoliticar.addAll(politicarList);
        politicarTableView.setItems(observableListPoliticar);
        politicarTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }

    public void dohvatiPoliticare(){

        HelloApplication.getMainStage().getScene().setOnKeyPressed(e-> {if (e.getCode()!= KeyCode.ENTER) return;});

        String ime = imePoliticaraTextField.getText();

        String prezime = prezimePoliticaraTextField.getText();


        Predicate<Politicar> predIme = politicar -> politicar.getIme().toLowerCase().contains(ime.toLowerCase());

        List<Politicar> filPoliticar= politicarList.stream().filter(predIme).collect(Collectors.toList());


        /*List<Student> filStudenti = studentList.stream()
                .filter(s->s.getJmbag().toLowerCase().contains(jmbag))
                .filter(s->s.getPrezime().toLowerCase().contains(prezime))
                .filter(s->s.getIme().toLowerCase().contains(ime))
                .toList();

        studentTableView.setItems(FXCollections.observableList(filStudenti));*/

        observableListPoliticar.clear();
        observableListPoliticar.addAll(filPoliticar);
    }

}
