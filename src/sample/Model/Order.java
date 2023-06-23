package sample.Model;

import java.sql.Timestamp;

public class Order {
    private int invoiceNumber;
    private Timestamp orderDate;
    private String customerID;
    private String termsCode;

    public Order() {
    }

    public Order(Timestamp orderDate, String customerID, String termsCode) {
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.termsCode = termsCode;
    }

    public Order(int invoiceNumber, Timestamp orderDate, String customerID, String termsCode) {
        this.invoiceNumber = invoiceNumber;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.termsCode = termsCode;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTermsCode() {
        return termsCode;
    }

    public void setTermsCode(String termsCode) {
        this.termsCode = termsCode;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
