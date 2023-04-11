package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

import com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    public PasswordField getPasswordField() {
        return passwordField;
    }

    //String username = usernameField.getText();
    //String password = passwordField.getText();


    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        List<User> listaUsera = new ArrayList<>();

        Main.readUserFromFile(listaUsera);

        HelloApplication m = new HelloApplication();
        for (int i = 0;i< listaUsera.size();i++) {
            Boolean repeatInput = false;
            do{
            try{
            if (usernameField.getText().toString().equals(listaUsera.get(i).getUsername().toString()) && passwordField.getText().toString().equals(listaUsera.get(i).getPassword())) {
                //wrongLogIn.setText("Success!");

                //m.changeScene("izbornik.fxml");

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("izbornik.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                HelloApplication.getMainStage().setTitle("test stranaka");
                HelloApplication.getMainStage().setScene(scene);
                HelloApplication.getMainStage().show();
                repeatInput = false;

            }}catch (InputMismatchException ex){
                repeatInput = true;
            if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
                logger.error("Username ili password nije unesen" + ex);
                //wrongLogIn.setText("Please enter your data.");
            }}}while(repeatInput);

        }
        /*
        HelloApplication m = new HelloApplication();
        if(usernameField.getText().toString().equals("javacoding") && passwordField.getText().toString().equals("123")) {
            //wrongLogIn.setText("Success!");

            //m.changeScene("izbornik.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("izbornik.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("test stranaka");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();

        }

         */


    }
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("section.io") && password.equals("123"))
            JOptionPane.showMessageDialog(null, "Login Successful");
        else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
    }*/

}
