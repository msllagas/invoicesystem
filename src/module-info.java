module InvoiceSystem {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens sample;
    opens sample.Controller;
    opens sample.Model;
}