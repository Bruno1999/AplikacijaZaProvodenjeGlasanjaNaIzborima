package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    private static Stage stg;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage=stage;
        Thread t1 = new Thread();
        t1.start();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginUsera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle(t1.getName());
        stage.setScene(scene);
        stage.show();
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("loginUsera.fxml"));
        primaryStage.setTitle("Aplikacija za provodenje glasanja na izborima!");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }*/

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getMainStage(){
        return mainStage;
    }
}