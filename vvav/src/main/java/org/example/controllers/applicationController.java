package org.example.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.domain.DBconnector;

import java.io.IOException;
import java.sql.*;

public class applicationController {

    @FXML
    private TextField enterEmail;

    @FXML
    private TextField enterPassword;

    @FXML
    private Button logInButton;





    public void log_in(ActionEvent actionEvent) {

        if (enterEmail.getText().isBlank() == false && enterPassword.getText().isBlank() == false) {
            try {
                Connection connection = DBconnector.getConnection();
                String verify = "SELECT email, password from person";
                ResultSet resultSet = connection.createStatement().executeQuery(verify);
                while (resultSet.next()){
                    if (enterEmail.getText().equals("anzhelika.vorob@gmail.com") && enterPassword.getText().equals("anzelika.vorob")){
                        App app = new App();
                        app.changeScene("tableViewPrivileges.fxml");
                    }
                    else if (resultSet.getString("email").equals(enterEmail.getText()) && resultSet.getString("password").equals(enterPassword.getText())){
                            App app = new App();
                            app.changeScene("tableView.fxml");
                        }
                    }
                } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String passEncode(String password){
        String pas = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return pas;
    }
}

