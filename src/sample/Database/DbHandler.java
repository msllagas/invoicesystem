package sample.Database;

import sample.Model.Customer;
import sample.Model.Order;
import sample.Model.OrderQuantity;

import java.sql.*;

public class DbHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public int getTotalCustomers() throws SQLException, ClassNotFoundException {
        String strQry = "SELECT COUNT(" + Const.customers_customerID + ") FROM " + Const.customersTable;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            return Integer.parseInt(resultSet.getString(1));
        }
        return resultSet.getInt(1);
    }
    public int getOrderQuantity() throws SQLException, ClassNotFoundException {
        String strQry = "SELECT SUM(" + Const.quantity_orderQuantity + ") FROM " + Const.orderQuantityTable;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }

    public float getTotalAmount() throws SQLException, ClassNotFoundException {
        String strQry = "SELECT SUM(" + Const.quantity_orderQuantity +  " * " +  Const.products_unitPrice + ") " +
                "FROM order_quantity orq, products p\n" +
                "WHERE orq.product_id = p.product_id";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getFloat(1);
        }
        return resultSet.getFloat(1);
    }
    public int getInvoice() throws SQLException, ClassNotFoundException {
        String strQry = "SELECT COUNT(" + Const.orders_invoiceNo +") FROM " + Const.ordersTable;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }
    public void addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String strQry = "INSERT INTO " + Const.customersTable + " (" + Const.customers_customerID + ", "+
                                        Const.customers_customerName + ", " + Const.customers_customerAddress + ", " +
                                        Const.customers_customerPWDNo + ", " + Const.customers_customerTINNo + ")" +
                                        " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);

        preparedStatement.setString(1, customer.getCustomerID());
        preparedStatement.setString(2, customer.getCustomerName());
        preparedStatement.setString(3, customer.getCustomerAddress());
        preparedStatement.setString(4, customer.getCustomerPWDNo());
        preparedStatement.setString(5, customer.getCustomerTINNo());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public int getCustomerOccurrence(String customerID) throws SQLException, ClassNotFoundException {
        String strQry = "SELECT COUNT(" + Const.customers_customerID + ") FROM " + Const.customersTable +
                        " WHERE " + Const.customers_customerID + " =?" ;
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setString(1, customerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        while (resultSet.next()){
            count = Integer.parseInt(resultSet.getString(1));
        }
        return count;
    }
    public int getProductID(String productDescription)throws SQLException, ClassNotFoundException {
        String strQry = "SELECT " + Const.products_productID + " FROM " + Const.productsTable +
                        " WHERE " + Const.products_productDesc + " =?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setString(1, productDescription);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }
    public void addOrder(Order order) throws SQLException, ClassNotFoundException {
        String strQry = "INSERT INTO " + Const.ordersTable + "(" + Const.orders_orderDate + ", " +
                        Const.orders_customerID + ", " + Const.orders_termsCode +") " + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setTimestamp(1, order.getOrderDate());
        preparedStatement.setString(2, order.getCustomerID());
        preparedStatement.setString(3, order.getTermsCode());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void addOrderQuantity(OrderQuantity orderQuantity) throws SQLException, ClassNotFoundException {
        String strQry = "INSERT INTO " + Const.orderQuantityTable + "(" + Const.quantity_invoiceNumber + ", " +
                Const.quantity_productID + ", " + Const.quantity_orderQuantity +") " + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setInt(1, orderQuantity.getInvoiceNumber());
        preparedStatement.setInt(2, orderQuantity.getProductID());
        preparedStatement.setInt(3, orderQuantity.getOrderQuantity());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public ResultSet joinTable() throws SQLException, ClassNotFoundException {
        String strQry = "SELECT orders.invoice_number, order_date,  customers.customer_id, customer_name, customer_address, customer_tin_number,  customer_pwd_number, products.product_id, products.product_description, products.fabric_type, order_quantity.order_quantity, products.unit_measure, products.unit_price, payment_method.terms_code, terms_description\n" +
                "FROM orders\n" +
                "JOIN customers\n" +
                "ON customers.customer_id = orders.customer_id\n" +
                "JOIN order_quantity\n" +
                "ON order_quantity.invoice_number = orders.invoice_number\n" +
                "JOIN products\n" +
                "ON products.product_id = order_quantity.product_id\n" +
                "JOIN payment_method\n" +
                "ON orders.terms_code = payment_method.terms_code\n" +
                "order by invoice_number";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        return preparedStatement.executeQuery();
    }
    public ResultSet getInfo(int invoiceID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String strQry = "SELECT orders.invoice_number, order_date,  customers.customer_id, customer_name, customer_address, customer_tin_number,  customer_pwd_number, products.product_description, order_quantity.order_quantity, payment_method.terms_code\n" +
                "FROM orders\n" +
                "JOIN customers\n" +
                "ON customers.customer_id = orders.customer_id\n" +
                "JOIN order_quantity\n" +
                "ON order_quantity.invoice_number = orders.invoice_number\n" +
                "JOIN products\n" +
                "ON products.product_id = order_quantity.product_id\n" +
                "JOIN payment_method\n" +
                "ON orders.terms_code = payment_method.terms_code\n" +
                "WHERE orders.invoice_number =?\n" +
                "order by invoice_number";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setInt(1, invoiceID);
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    public ResultSet searchCustomer(String customerID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String strQry = "SELECT orders.invoice_number, order_date,  customers.customer_id, customer_name, customer_address, customer_tin_number,  customer_pwd_number, products.product_id, products.product_description, products.fabric_type, order_quantity.order_quantity, products.unit_measure, products.unit_price, payment_method.terms_code, terms_description\n" +
                "FROM orders\n" +
                "JOIN customers\n" +
                "ON customers.customer_id = orders.customer_id \n" +
                "JOIN order_quantity\n" +
                "ON order_quantity.invoice_number = orders.invoice_number\n" +
                "JOIN products\n" +
                "ON products.product_id = order_quantity.product_id\n" +
                "JOIN payment_method\n" +
                "ON orders.terms_code = payment_method.terms_code\n" +
                "WHERE orders.customer_id =?\n" +
                "order by invoice_number";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(strQry);
        preparedStatement.setString(1, customerID);
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
}
