package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class detailedViewController implements Initializable {
        @FXML
        private TableView<users> table;

        @FXML
        private TableColumn<users, Integer> idColumn;

        @FXML
        private TableColumn<users, String> nameColumn;

        @FXML
        private TableColumn<users, String> surnameColumn;

        @FXML
        private TableColumn<users, String> emailColumn;

        @FXML
        private TableColumn<users, String> cityColumn;

        @FXML
        private TableColumn<users, String> streetColumn;

        ObservableList<users> observableList = FXCollections.observableArrayList();


        public void go_back(ActionEvent actionEvent) {
                App app = new App();
                try {
                        app.changeScene("tableViewPrivileges.fxml");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                try {
                        Connection conn = DBconnector.getConnection();
                        ResultSet rs = conn.createStatement().executeQuery("select *, \"address\".city, \"address\".street \n" +
                                "from person p join person_has_address ph on p.person_id = ph.person_id join \n" +
                                "address on \"address\".address_id = ph.address_id\n");
                        while (rs.next()){
                                observableList.add(new users(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"),
                                        rs.getString("email"), rs.getString("city"), rs.getString("street")));}
                } catch (SQLException e) {
                        e.printStackTrace();
                }

                idColumn.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<users, String>("name"));
                surnameColumn.setCellValueFactory(new PropertyValueFactory<users, String>("surname"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
                streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));


                table.setItems(observableList);

        }
}

