package sample.Model;

import java.sql.Timestamp;

public class Table {
    private int invoiceNo;
    private Timestamp orderDate;
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerTINNo;
    private String customerPWDNo;
    private int productID;
    private String productDesc;
    private String fabricType;
    private int orderQuantity;
    private String unitMeasure;
    private float unitPrice;
    private String termsCode;
    private String termsDesc;

    public Table() {
    }

    public Table(int invoiceNo, Timestamp orderDate, String customerID, String customerName,
                 String customerAddress, String customerTINNo, String customerPWDNo, int productID,
                 String productDesc, String fabricType, int orderQuantity, String unitMeasure,
                 float unitPrice, String termsCode, String termsDesc) {
        this.invoiceNo = invoiceNo;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTINNo = customerTINNo;
        this.customerPWDNo = customerPWDNo;
        this.productID = productID;
        this.productDesc = productDesc;
        this.fabricType = fabricType;
        this.orderQuantity = orderQuantity;
        this.unitMeasure = unitMeasure;
        this.unitPrice = unitPrice;
        this.termsCode = termsCode;
        this.termsDesc = termsDesc;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public Table setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public Table setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Table setCustomerID(String customerID) {
        this.customerID = customerID;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Table setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public Table setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public String getCustomerTINNo() {
        return customerTINNo;
    }

    public Table setCustomerTINNo(String customerTINNo) {
        this.customerTINNo = customerTINNo;
        return this;
    }

    public String getCustomerPWDNo() {
        return customerPWDNo;
    }

    public Table setCustomerPWDNo(String customerPWDNo) {
        this.customerPWDNo = customerPWDNo;
        return this;
    }

    public int getProductID() {
        return productID;
    }

    public Table setProductID(int productID) {
        this.productID = productID;
        return this;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Table setProductDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }

    public String getFabricType() {
        return fabricType;
    }

    public Table setFabricType(String fabricType) {
        this.fabricType = fabricType;
        return this;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public Table setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public Table setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
        return this;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public Table setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public String getTermsCode() {
        return termsCode;
    }

    public Table setTermsCode(String termsCode) {
        this.termsCode = termsCode;
        return this;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public Table setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
        return this;
    }
}
