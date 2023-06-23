package sample.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Database.DbHandler;

public class InfoController {
    DbHandler dbHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblInvoiceNo;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblPWDNo;

    @FXML
    private Label lblTINNo;

    @FXML
    private TextArea taAddress;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblTermsCode;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblProductDesc;

    @FXML
    private Label lblOrderQuantity;

    @FXML
    private Button btnDone;


    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) btnDone.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        TableController tableController = new TableController();

        dbHandler = new DbHandler();

        ResultSet resultSet = dbHandler.getInfo(TableController.id);
        while (resultSet.next()){
            lblInvoiceNo.setText(resultSet.getString("invoice_number"));
            lblOrderDate.setText(String.valueOf(resultSet.getTimestamp("order_date")));
            lblCustomerID.setText(resultSet.getString("customer_id"));
            lblTermsCode.setText(resultSet.getString("terms_code"));
            lblCustomerName.setText(resultSet.getString("customer_name"));
            taAddress.setText(resultSet.getString("customer_address"));
            lblPWDNo.setText(resultSet.getString("customer_pwd_number"));
            lblTINNo.setText(resultSet.getString("customer_tin_number"));
            lblProductDesc.setText(resultSet.getString("product_description"));
            lblOrderQuantity.setText(resultSet.getString("order_quantity"));
        }

    }
}
