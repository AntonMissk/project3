module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires bcrypt;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    opens org.example.controllers to javafx.fxml;
    exports org.example.domain;
    opens org.example.domain to javafx.fxml;
}