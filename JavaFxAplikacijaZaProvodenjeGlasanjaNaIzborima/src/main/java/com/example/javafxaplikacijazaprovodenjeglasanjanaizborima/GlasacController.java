package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Glasac;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GlasacController implements Initializable {
    private static Connection connection;
    private static List<Glasac> glasacList = new ArrayList<>();
    static {
        try {
            connection = BazaPodataka.connectToDatabase();
            glasacList = BazaPodataka.getAllGlasacFromDatabase(connection);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Glasac> observableListGlasac;


    @FXML
    private TextField imeGlasacaTextField;
    @FXML
    private TextField prezimeGlasacaTextField;
    @FXML
    private TextField jmbagGlasacaTextField;
    @FXML
    private DatePicker datumRodenjaDatePicker;

    @FXML
    private TextField izbornoMjestoGlasacaTextField;
    @FXML
    private TextField glasaoGlasacTextField;
    @FXML
    private TableView<Glasac> glasacTableView;
    @FXML
    private TableColumn<Glasac,String> imeGlasacaTableColumn;
    @FXML
    private TableColumn<Glasac,String> prezimeGlasacaTableColumn;
    @FXML
    private TableColumn<Glasac,String> jmbagGlasacaTableColumn;
    @FXML
    private TableColumn<Glasac,String> datumRodenjaGlasacaTableColumn;

    @FXML
    private TableColumn<Glasac,String> izbornoMjestoGlasacaTableColumn;
    @FXML
    private TableColumn<Glasac,String> glasaoGlasacaTableColumn;

    public void initialize(URL url, ResourceBundle resourceBundle){

        imeGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getIme()));
        prezimeGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getPrezime()));
        jmbagGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getJmbag()));
        datumRodenjaGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getDatumRodenja().toString()));
        izbornoMjestoGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getIzbornoMjesto()));
        glasaoGlasacaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getGlasao().toString()));

        if (observableListGlasac == null){
            observableListGlasac = FXCollections.observableArrayList();
        }

        observableListGlasac.addAll(glasacList);
        glasacTableView.setItems(observableListGlasac);
        glasacTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        glasacTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                Glasac glasac = glasacTableView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void dohvatiGlasace(){

        HelloApplication.getMainStage().getScene().setOnKeyPressed(e-> {if (e.getCode()!= KeyCode.ENTER) return;});

        String ime = imeGlasacaTextField.getText();

        String prezime = prezimeGlasacaTextField.getText();

        String jmbag = jmbagGlasacaTextField.getText();

        String izbornoMjesto = izbornoMjestoGlasacaTextField.getText();

        String glasao = glasaoGlasacTextField.getText();

        Predicate<Glasac> predIme = glasac -> glasac.getIme().toLowerCase().contains(ime.toLowerCase());

        List<Glasac> filGlasaci= glasacList.stream().filter(predIme).collect(Collectors.toList());


        /*List<Student> filStudenti = studentList.stream()
                .filter(s->s.getJmbag().toLowerCase().contains(jmbag))
                .filter(s->s.getPrezime().toLowerCase().contains(prezime))
                .filter(s->s.getIme().toLowerCase().contains(ime))
                .toList();

        studentTableView.setItems(FXCollections.observableList(filStudenti));*/

        observableListGlasac.clear();
        observableListGlasac.addAll(filGlasaci);
    }

    public void buttonRemove(ActionEvent event){
        ObservableList<Glasac> glasacs,pojediniGlasac;
        glasacs = glasacTableView.getItems();
        pojediniGlasac = glasacTableView.getSelectionModel().getSelectedItems();
        pojediniGlasac.forEach(pojediniGlasac::remove);

    }

    public void deleteGlasac(Glasac glasacToDelete) throws SQLException, IOException {
        Connection connection = BazaPodataka.connectToDatabase();

        PreparedStatement deleteGlasac =
                connection.prepareStatement(
                        "DELETE FROM GLASAC WHERE JMBAG = ?");

        deleteGlasac.setString(1, glasacToDelete.getJmbag());

        deleteGlasac.executeUpdate();

        connection.close();
    }
    /*
    public void deleteGlasac(Glasac glasacToDelete) throws SQLException, IOException {
        Connection connection = BazaPodataka.connectToDatabase();

        PreparedStatement deleteGlasac =
                connection.prepareStatement(
                        "DELETE FROM GLASAC WHERE JMBAG = ?");

        deleteGlasac.setString(1, glasacToDelete.getJmbag());

        deleteGlasac.executeUpdate();

        connection.close();
    }*/


}
