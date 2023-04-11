package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
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

public class StrankaController implements Initializable {

    private static Connection connection;
    private static List<Stranka> strankaList = new ArrayList<>();
    static {
        try {
            connection = BazaPodataka.connectToDatabase();
            strankaList = BazaPodataka.getAllStrankaFromDatabase(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Stranka> observableListStranka;


    @FXML
    private TextField nazivStrankaTextField;
    @FXML
    private TextField adresaStrankaTextField;

    @FXML
    private TableView<Stranka> strankaTableView;
    @FXML
    private TableColumn<Stranka,String> nazivStrankeTableColumn;
    @FXML
    private TableColumn<Stranka,String> adresaStrankeTableColumn;


    public void initialize(URL url, ResourceBundle resourceBundle){

        nazivStrankeTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getNaziv()));
        adresaStrankeTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAdresa().toString()));

        if (observableListStranka == null){
            observableListStranka = FXCollections.observableArrayList();
        }

        observableListStranka.addAll(strankaList);
        strankaTableView.setItems(observableListStranka);
        strankaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }

    public void dohvatiStranke(){

        HelloApplication.getMainStage().getScene().setOnKeyPressed(e-> {if (e.getCode()!= KeyCode.ENTER) return;});

        String naziv = nazivStrankaTextField.getText();

        String adresa = adresaStrankaTextField.getText();


        Predicate<Stranka> predIme = stranka -> stranka.getNaziv().toLowerCase().contains(naziv.toLowerCase());

        List<Stranka> filStranka= strankaList.stream().filter(predIme).collect(Collectors.toList());


        /*List<Student> filStudenti = studentList.stream()
                .filter(s->s.getJmbag().toLowerCase().contains(jmbag))
                .filter(s->s.getPrezime().toLowerCase().contains(prezime))
                .filter(s->s.getIme().toLowerCase().contains(ime))
                .toList();

        studentTableView.setItems(FXCollections.observableList(filStudenti));*/

        observableListStranka.clear();
        observableListStranka.addAll(filStranka);
    }


}
