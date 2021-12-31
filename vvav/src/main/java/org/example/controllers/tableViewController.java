package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.domain.DBconnector;
import org.example.domain.users;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tableViewController implements Initializable {

    @FXML
    private TableView<users> tableView;

    @FXML
    private TableColumn<users, Integer> idColumn;

    @FXML
    private TableColumn<users, String> nameColumn;

    @FXML
    private TableColumn<users, String> lastNameColumn;

    @FXML
    private TableColumn<users, String> emailColumn;

    @FXML
    private Button goBackButton;


    public void go_back (Event event) {
        App app = new App();
        try {
            app.changeScene("application.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<users> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
        Connection conn = DBconnector.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM person");
        while (rs.next()){
            oblist.add(new users(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("email")));}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<users, String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<users, String>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(oblist);

    }
}
