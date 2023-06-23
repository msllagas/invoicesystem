package sample.Database;

public class Const {
    // tables
    public static final String customersTable = "customers";
    public static final String ordersTable = "orders";
    public static final String paymentMethodTable = "payment_method";
    public static final String productsTable = "products";
    public static final String orderQuantityTable = "order_quantity";

    // customers' table
    public static final String customers_customerID = "customer_id";
    public static final String customers_customerName = "customer_name";
    public static final String customers_customerAddress ="customer_address";
    public static final String customers_customerPWDNo = "customer_pwd_number";
    public static final String customers_customerTINNo = "customer_tin_number";

    // orders' table
    public static final String orders_invoiceNo = "invoice_number";
    public static final String orders_orderDate = "order_date";
    public static final String orders_customerID = "customer_id";
    public static final String orders_termsCode = "terms_code";

    // payment method table
    public static final String paymentMethod_termsCode = "terms_code";
    public static final String paymentMethod_termsDesc = "terms_description";

    // products' table
    public static final String products_productID = "product_id";
    public static final String products_productDesc = "product_description";
    public static final String products_fabricType = "fabric_type";
    public static final String products_unitMeasure = "unit_measure";
    public static final String products_unitPrice = "unit_price";

    // order quantity table
    public static final String quantity_invoiceNumber = "invoice_number";
    public static final String quantity_productID = "product_id";
    public static final String quantity_orderQuantity = "order_quantity";


}
