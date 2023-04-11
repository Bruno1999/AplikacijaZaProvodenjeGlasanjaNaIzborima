package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.baza.BazaPodataka;
import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.Politicar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GlasanjeController implements Initializable {


    @FXML
    private RadioButton rButton;

    @FXML
    private RadioButton rButton2;

    @FXML
    private RadioButton rButton3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){}

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        rButton.setToggleGroup(group);
        rButton2.setToggleGroup(group);
        rButton3.setToggleGroup(group);
    }

    public void spremiGlas() {

        ToggleGroup group = new ToggleGroup();


        Boolean buttonOne = rButton.isSelected();
        Boolean buttonTwo = rButton2.isSelected();
        Boolean buttonThree= rButton3.isSelected();

        StringBuilder errorMessages = new StringBuilder();

        long id = 0L;

        Connection connectionID;
        {
            try {
                connectionID = BazaPodataka.connectToDatabase();
                List<Politicar> politicarList = BazaPodataka.getAllPoliticarFromDatabase(connectionID);
                id = (long) (politicarList.size() + 1);

                if (buttonOne = true){
                    /*Integer suma1 = politicarList.get(1).getSumaGlasova();
                    suma1 += 1;*/
                    Politicar newPoliticar = new Politicar(politicarList.get(1).getId(),politicarList.get(1).getIme(),politicarList.get(1).getPrezime(),politicarList.get(1).getSumaGlasova()+1);
                    BazaPodataka.insertNewPoliticarToDatabase(newPoliticar);
                } else if (buttonTwo = true) {
                    /*Integer suma2 = politicarList.get(2).getSumaGlasova();
                    suma2 += 1;*/
                    Politicar newPoliticar = new Politicar(politicarList.get(2).getId(),politicarList.get(2).getIme(),politicarList.get(2).getPrezime(),politicarList.get(2).getSumaGlasova()+1);
                    BazaPodataka.insertNewPoliticarToDatabase(newPoliticar);
                }else if (buttonThree = true) {
                    /*Integer suma3=politicarList.get(3).getSumaGlasova();
                    suma3 += 1;*/
                    Politicar newPoliticar = new Politicar(politicarList.get(3).getId(),politicarList.get(3).getIme(),politicarList.get(3).getPrezime(),politicarList.get(3).getSumaGlasova()+1);
                    BazaPodataka.insertNewPoliticarToDatabase(newPoliticar);
                }


            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

        String rButtonText = rButton.getText();

        if(rButtonText.isEmpty()) {
            errorMessages.append("Button should be selected!\n");
        }

        String rButton2Text = rButton2.getText();

        if(rButton2Text.isEmpty()) {
            errorMessages.append("Button should be selected!\n");
        }

        String rButton3Text = rButton3.getText();

        if(rButton3Text.isEmpty()) {
            errorMessages.append("Button shoulf be selected!\n");
        }

        if(errorMessages.isEmpty()) {

            /*if (buttonOne = true){
                Integer suma1 = politicarList.get(1).getSumaGlasova();
                suma1 += 1;
                Politicar newPoliticar = new Politicar(1,"Andrej","Plenković",suma1);
            } else if (buttonTwo = true) {
                Integer suma2 = politicarList.get(2).getSumaGlasova();
                suma2 += 1;
                Politicar newPoliticar = new Politicar(2,"Zoran","Milanović",suma2);

            }else if (buttonThree = true) {
                Integer suma3=politicarList.get(3).getSumaGlasova();
                suma3 += 1;
                Politicar newPoliticar = new Politicar(3,"Tomislav","Tomašević",suma3);

            }

            Connection connection;
            {
                try {
                    connection = BazaPodataka.connectToDatabase();
                    BazaPodataka.insertNewPoliticarToDatabase(newPoliticar);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }*/

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save action succeded!");
            alert.setHeaderText("Politicar data saved!");
            alert.setContentText("Politicar saved to the database!");

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
