package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class IzbornikController {


    public void showGlasacSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaGlasaca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Pretraga glasaca");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void showStrankaSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaStranaka.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Pretraga stranaka");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
    public void showAddGlasac() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosGlasaca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Unos glasaca");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void showPoliticarSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pretragaPoliticara.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Pretraga politicara");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
    public void showAddPoliticara() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosPoliticara.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Unos politicara");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void showAddStranka() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("unosStranaka.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Unos stranaka");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void showDeleteGlasac() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("brisanjeGlasaca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Brisanje glasaca");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }

    public void choosePoliticarVote() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("glasanje.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Glasanje");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();

    }
}
