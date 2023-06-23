package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Database.DbHandler;
import sample.Model.Table;

public class TableController {
    DbHandler dbHandler;

    public static int id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnShowInfo;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblProductSold;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<Table> tvInvoice;

    @FXML
    private TableColumn<Table, Integer> colInvoiceNumber;

    @FXML
    private TableColumn<Table, Timestamp> colOrderDate;

    @FXML
    private TableColumn<Table, String> colCustomerID;

    @FXML
    private TableColumn<Table, String> colCustomerName;

    @FXML
    private TableColumn<Table, String> colCustomerAddress;

    @FXML
    private TableColumn<Table, String> colCustomerTINNo;

    @FXML
    private TableColumn<Table, String> colCustomerPWDNo;

    @FXML
    private TableColumn<Table, Integer> colProductID;

    @FXML
    private TableColumn<Table, String> colProductDesc;

    @FXML
    private TableColumn<Table, String> colFabricType;

    @FXML
    private TableColumn<Table, Integer> colOrderQuantity;

    @FXML
    private TableColumn<Table, String> colUnitMeasure;

    @FXML
    private TableColumn<Table, Float> colUnitPrice;

    @FXML
    private TableColumn<Table, String> colTermsCode;

    @FXML
    private TableColumn<Table, String> colTermsDesc;


    @FXML
    void addData(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sample/View/dataForm.fxml"));
        Parent root = fxmlLoader.load();
        FormController formController = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        getTotalQuantity();
        getTotalCustomer();
        getTotalAmount();
        showTable();
    }

    @FXML
    void logout(ActionEvent event) {
        btnLogout.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/View/loginForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginController loginController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }
    @FXML
    void showInfo(ActionEvent event) {
        if (tvInvoice.getSelectionModel().getSelectedItem() != null) {
            Table selectedTable = tvInvoice.getSelectionModel().getSelectedItem();
            id = selectedTable.getInvoiceNo();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/info.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InfoController infoController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a row.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    @FXML
    void searchCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!tfSearch.getText().isEmpty()){
            Table table;
            ObservableList<Table> searchCust = FXCollections.observableArrayList();
            dbHandler = new DbHandler();
            ResultSet resultSet = dbHandler.searchCustomer(tfSearch.getText());
            while (resultSet.next()){
                table = new Table(resultSet.getInt("invoice_number"), resultSet.getTimestamp("order_date"),
                        resultSet.getString("customer_id"), resultSet.getString("customer_name"),
                        resultSet.getString("customer_address"), resultSet.getString("customer_tin_number"),
                        resultSet.getString("customer_pwd_number"), resultSet.getInt("product_id"),
                        resultSet.getString("product_description"), resultSet.getString("fabric_type"),
                        resultSet.getInt("order_quantity"), resultSet.getString("unit_measure"),
                        resultSet.getFloat("unit_price"), resultSet.getString("terms_code"),
                        resultSet.getString("terms_description"));
                searchCust.add(table);
            }
            colInvoiceNumber.setCellValueFactory(new PropertyValueFactory<Table, Integer>("invoiceNo"));
            colOrderDate.setCellValueFactory(new PropertyValueFactory<Table, Timestamp>("orderDate"));
            colCustomerID.setCellValueFactory(new PropertyValueFactory<Table, String>("customerID"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<Table, String>("customerName"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<Table, String>("customerAddress"));
            colCustomerTINNo.setCellValueFactory(new PropertyValueFactory<Table, String>("customerTINNo"));
            colCustomerPWDNo.setCellValueFactory(new PropertyValueFactory<Table, String>("customerPWDNo"));
            colProductID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("productID"));
            colProductDesc.setCellValueFactory(new PropertyValueFactory<Table, String>("productDesc"));
            colFabricType.setCellValueFactory(new PropertyValueFactory<Table, String>("fabricType"));
            colOrderQuantity.setCellValueFactory(new PropertyValueFactory<Table, Integer>("orderQuantity"));
            colUnitMeasure.setCellValueFactory(new PropertyValueFactory<Table, String>("unitMeasure"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<Table, Float>("unitPrice"));
            colTermsCode.setCellValueFactory(new PropertyValueFactory<Table, String>("termsCode"));
            colTermsDesc.setCellValueFactory(new PropertyValueFactory<Table, String>("termsDesc"));

            tvInvoice.setItems(searchCust);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Empty search field");
            alert.setHeaderText(null);
            alert.showAndWait();
            showTable();
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        showTable();
        getTotalCustomer();
        getTotalQuantity();
        getTotalAmount();
    }
    private void getTotalCustomer() throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();

        int count = 0;
        count = dbHandler.getTotalCustomers();

        lblTotalCustomers.setText(String.valueOf(count));
    }
    private void getTotalQuantity() throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();

        int count = 0;
        count = dbHandler.getOrderQuantity();

        lblProductSold.setText("" + count);
    }
    private void getTotalAmount() throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        lblTotalAmount.setText("₱" + decimalFormat.format(dbHandler.getTotalAmount()));

    }
    private void showTable() throws SQLException, ClassNotFoundException {
        Table table;
        ObservableList<Table> joinTable = FXCollections.observableArrayList();
        dbHandler = new DbHandler();
        ResultSet resultSet = dbHandler.joinTable();
        while (resultSet.next()){
            table = new Table(resultSet.getInt("invoice_number"), resultSet.getTimestamp("order_date"),
                            resultSet.getString("customer_id"), resultSet.getString("customer_name"),
                            resultSet.getString("customer_address"), resultSet.getString("customer_tin_number"),
                            resultSet.getString("customer_pwd_number"), resultSet.getInt("product_id"),
                            resultSet.getString("product_description"), resultSet.getString("fabric_type"),
                            resultSet.getInt("order_quantity"), resultSet.getString("unit_measure"),
                            resultSet.getFloat("unit_price"), resultSet.getString("terms_code"),
                            resultSet.getString("terms_description"));
            joinTable.add(table);
        }
        colInvoiceNumber.setCellValueFactory(new PropertyValueFactory<Table, Integer>("invoiceNo"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<Table, Timestamp>("orderDate"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<Table, String>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Table, String>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<Table, String>("customerAddress"));
        colCustomerTINNo.setCellValueFactory(new PropertyValueFactory<Table, String>("customerTINNo"));
        colCustomerPWDNo.setCellValueFactory(new PropertyValueFactory<Table, String>("customerPWDNo"));
        colProductID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("productID"));
        colProductDesc.setCellValueFactory(new PropertyValueFactory<Table, String>("productDesc"));
        colFabricType.setCellValueFactory(new PropertyValueFactory<Table, String>("fabricType"));
        colOrderQuantity.setCellValueFactory(new PropertyValueFactory<Table, Integer>("orderQuantity"));
        colUnitMeasure.setCellValueFactory(new PropertyValueFactory<Table, String>("unitMeasure"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<Table, Float>("unitPrice"));
        colTermsCode.setCellValueFactory(new PropertyValueFactory<Table, String>("termsCode"));
        colTermsDesc.setCellValueFactory(new PropertyValueFactory<Table, String>("termsDesc"));

        tvInvoice.setItems(joinTable);
    }

}
