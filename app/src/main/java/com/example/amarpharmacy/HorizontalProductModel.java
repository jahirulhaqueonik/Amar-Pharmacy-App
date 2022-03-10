package com.example.amarpharmacy;

public class HorizontalProductModel {

    private String productID;
    private String productImage;
    private String productTitle;
    private String productPrice;

    public HorizontalProductModel(String productID, String productImages, String productTitle, String productPrice) {
        this.productID = productID;
        this.productImage = productImages;
        this.productTitle = productTitle;
        this.productPrice = productPrice;

    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
