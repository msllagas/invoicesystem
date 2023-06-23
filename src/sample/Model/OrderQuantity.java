package sample.Model;

public class OrderQuantity {
    private int invoiceNumber;
    private int productID;
    private int orderQuantity;

    public OrderQuantity() {
    }

    public OrderQuantity(int invoiceNumber, int productID, int orderQuantity) {
        this.invoiceNumber = invoiceNumber;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
