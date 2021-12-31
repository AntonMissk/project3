package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.domain.DBconnector;
import org.example.domain.users;
import org.example.domain.usersDao;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class tableViewPrivilegesController implements Initializable {

    @FXML
    private TableView <users> table;

    @FXML
    private TableColumn <users, Integer> tableId;

    @FXML
    private TableColumn<users, String> tableName;

    @FXML
    private TableColumn<users, String> tableSurname;

    @FXML
    private TableColumn<users, String> tableEmail;

    @FXML
    private TextArea resultConsole;

    @FXML
    private Button searchButton;

    @FXML
    private Button searchAllButton;

    @FXML
    private TextField searchSurname;

    @FXML
    private TextField addName;

    @FXML
    private TextField addSurname;

    @FXML
    private TextField addEmail;

    @FXML
    private TextField addPassword;

    @FXML
    private TextField deleteIdUser;

    @FXML
    private TextField updateEmail;


    public void addUser(ActionEvent actionEvent) {
        try {
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, addPassword.getText().toCharArray());
            usersDao.insertUser(addName.getText(),addSurname.getText(),addEmail.getText(), bcryptHashString);
            resultConsole.setText("Employee inserted! \n");
        } catch (SQLException e) {
            resultConsole.setText("Problem occurred while inserting employee " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setUserInfoToTextArea ( users user) {
        resultConsole.setText("First Name: " + user.getName() + "\n" +
                "Last Name: " + user.getSurname());
    }




    public void searchUser(ActionEvent actionEvent) {
        users user = null;
        try {
            user = usersDao.searchUser(searchSurname.getText());
            populateAndShowEmployee(user);
        }
        catch (SQLException e)
        {e.printStackTrace();}
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateUser(ActionEvent actionEvent) {
        try {
            usersDao.updateUserEmail(searchSurname.getText(),updateEmail.getText());
            resultConsole.setText("Email has been updated for, user surname: " + searchSurname.getText() + "\n");
        } catch (SQLException e) {
            resultConsole.setText("Problem occurred while updating email: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void populateUsers (ObservableList<users> usersData) throws ClassNotFoundException {
        table.setItems(usersData);
    }

   public void searchAllUsers(ActionEvent actionEvent) {
       try {
           DBconnector.getConnection().close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       databaseView();

   }


    public void deleteUser(ActionEvent actionEvent) {
        try {
            usersDao.deleteUsserId(deleteIdUser.getText());
            resultConsole.setText("Employee deleted! Employee id: " + deleteIdUser.getText() + "\n");
        } catch (SQLException e) {
            resultConsole.setText("Problem occurred while deleting employee " + e);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void detailedView(ActionEvent actionEvent) {
        App app = new App();
        try {
            app.changeScene("detailedView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void databaseView(){
        try {
            ResultSet rs = DBconnector.getConnection().createStatement().executeQuery("SELECT * FROM person");
            while (rs.next()){
                observableList.add(new users(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email")));}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableId.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<users, String>("name"));
        tableSurname.setCellValueFactory(new PropertyValueFactory<users, String>("surname"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.setItems(observableList);
    }



    ObservableList<users> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseView();
    }

    private void populateAndShowEmployee(users us) throws ClassNotFoundException {
        if (us != null) {
            populateUser(us);
            setUserInfoToTextArea(us);
        } else {
            resultConsole.setText("This employee does not exist!\n");
        }
    }

    private void populateUser (users user) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<users> userData = FXCollections.observableArrayList();
        userData.add(user);
        table.setItems(userData);
    }

    public void logout(ActionEvent actionEvent) {
        App app = new App();
        try {
            app.changeScene("application.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

