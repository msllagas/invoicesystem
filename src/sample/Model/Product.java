package sample.Model;

public class Product {
    private int productID;
    private String productDesc;

    public Product() {
    }

    public Product(int productID, String productDesc) {
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public int getProductID() {
        return productID;
    }

    public Product setProductID(int productID) {
        this.productID = productID;
        return this;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public Product setProductDesc(String productDesc) {
        this.productDesc = productDesc;
        return this;
    }
}
