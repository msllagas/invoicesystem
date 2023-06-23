package sample.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Database.DbHandler;
import sample.Model.Customer;
import sample.Model.Order;
import sample.Model.OrderQuantity;

public class FormController {
    DbHandler dbHandler;

    @FXML
    private Label lblInvoiceNo;

    @FXML
    private Label lblOrderDate;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfPWDNo;

    @FXML
    private TextField tfTINNo;

    @FXML
    private TextField tfCustomerID;

    @FXML
    private TextField tfQuantity;

    @FXML
    private MenuButton mbTerms;

    @FXML
    private MenuItem miEP;

    @FXML
    private MenuItem miPOR;

    @FXML
    private MenuButton mbProduct;

    @FXML
    private MenuItem miWedDress;

    @FXML
    private MenuItem miMenCoat;

    @FXML
    private MenuItem miBarong;

    @FXML
    private MenuItem miLongSleeves;

    @FXML
    private MenuItem miEveningGown;

    @FXML
    private MenuItem miCocktailDress;

    @FXML
    private MenuItem miNecktie;

    @FXML
    private MenuItem miSlacks;

    @FXML
    private MenuItem miFilipinianaDress;

    @FXML
    private MenuItem miBallGown;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    @FXML
    void enterData(ActionEvent event) throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();
        if (tfCustomerID.getText().isEmpty() && tfName.getText().isEmpty() && mbTerms.getText().isEmpty() &&
                mbProduct.getText().isEmpty() && tfAddress.getText().isEmpty() && tfQuantity.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill up the form.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        if (!tfCustomerID.getText().isEmpty() && !mbProduct.getText().isEmpty() && !tfQuantity.getText().isEmpty() &&
                dbHandler.getCustomerOccurrence(tfCustomerID.getText()) > 0 && !mbTerms.getText().isEmpty() ){
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            Customer customer = new Customer();
            customer.setCustomerID(tfCustomerID.getText());
            customer.setCustomerName(tfName.getText());
            customer.setCustomerAddress(tfAddress.getText());
            customer.setCustomerPWDNo(tfPWDNo.getText());
            customer.setCustomerTINNo(tfTINNo.getText());

            Order order = new Order();
            order.setOrderDate(timestamp);
            order.setCustomerID(tfCustomerID.getText());
            order.setTermsCode(mbTerms.getText());

            OrderQuantity orderQuantity = new OrderQuantity();
            orderQuantity.setInvoiceNumber(Integer.parseInt(lblInvoiceNo.getText()));
            orderQuantity.setProductID(dbHandler.getProductID(mbProduct.getText()));
            orderQuantity.setOrderQuantity(Integer.parseInt(tfQuantity.getText()));

            dbHandler.addOrder(order);
            dbHandler.addOrderQuantity(orderQuantity);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Successfully added to the database");
            alert.setHeaderText(null);
            alert.showAndWait();
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }
        if (!tfCustomerID.getText().isEmpty() && !tfName.getText().isEmpty() && !mbTerms.getText().isEmpty() &&
                !mbProduct.getText().isEmpty() && !tfAddress.getText().isEmpty() && !tfQuantity.getText().isEmpty() && dbHandler.getCustomerOccurrence(tfCustomerID.getText()) == 0) {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            Customer customer = new Customer();
            customer.setCustomerID(tfCustomerID.getText());
            customer.setCustomerName(tfName.getText());
            customer.setCustomerAddress(tfAddress.getText());
            customer.setCustomerPWDNo(tfPWDNo.getText());
            customer.setCustomerTINNo(tfTINNo.getText());

            Order order = new Order();
            order.setOrderDate(timestamp);
            order.setCustomerID(tfCustomerID.getText());
            order.setTermsCode(mbTerms.getText());

            OrderQuantity orderQuantity = new OrderQuantity();
            orderQuantity.setInvoiceNumber(Integer.parseInt(lblInvoiceNo.getText()));
            orderQuantity.setProductID(dbHandler.getProductID(mbProduct.getText()));
            orderQuantity.setOrderQuantity(Integer.parseInt(tfQuantity.getText()));
            dbHandler.addCustomer(customer);

            dbHandler.addOrder(order);
            dbHandler.addOrderQuantity(orderQuantity);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Successfully added to the database");
            alert.setHeaderText(null);
            alert.showAndWait();
//            try {
//                if (dbHandler.getCustomerOccurrence(tfCustomerID.getText()) == 0) {
//                    dbHandler.addCustomer(customer);
//                }
//                dbHandler.addOrder(order);
//                dbHandler.addOrderQuantity(orderQuantity);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                alert.setContentText("Successfully added to the database");
//                alert.setHeaderText(null);
//                alert.showAndWait();
//            } catch (SQLException exception) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setContentText(exception.getSQLState());
//                alert.setHeaderText(null);
//                alert.showAndWait();
//            }
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        dbHandler = new DbHandler();
        lblInvoiceNo.setText(String.valueOf(dbHandler.getInvoice() + 1));

        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        lblOrderDate.setText(String.valueOf(date));

        productsMenu();
        termsMenu();
        addTextLimiter(tfPWDNo, 7);
        addTextLimiter(tfTINNo, 16);

    }
    private void productsMenu(){
        MenuItem[] products = {miWedDress, miMenCoat, miBarong, miLongSleeves,
                                miEveningGown, miCocktailDress, miNecktie,
                                miSlacks, miFilipinianaDress, miBallGown};

        for (MenuItem menuItem : products){
            menuItem.setOnAction(actionEvent -> {
                mbProduct.setText(menuItem.getText());
            });
        }
    }
    private void termsMenu(){
        MenuItem[] terms = {miEP, miPOR};

        for (MenuItem menuItem : terms){
            menuItem.setOnAction(actionEvent -> {
                mbTerms.setText(menuItem.getText());
            });
        }
    }
    public void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
