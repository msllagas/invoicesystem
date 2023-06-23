package sample.Model;

public class Customer {
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPWDNo;
    private String customerTINNo;

    public Customer() {

    }

    public Customer(String customerName, String customerAddress, String customerPWDNo, String customerTINNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPWDNo = customerPWDNo;
        this.customerTINNo = customerTINNo;
    }

    public Customer(String customerID, String customerName, String customerAddress, String customerPWDNo, String customerTINNo) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPWDNo = customerPWDNo;
        this.customerTINNo = customerTINNo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPWDNo() {
        return customerPWDNo;
    }

    public void setCustomerPWDNo(String customerPWDNo) {
        this.customerPWDNo = customerPWDNo;
    }

    public String getCustomerTINNo() {
        return customerTINNo;
    }

    public void setCustomerTINNo(String customerTINNo) {
        this.customerTINNo = customerTINNo;
    }
}
