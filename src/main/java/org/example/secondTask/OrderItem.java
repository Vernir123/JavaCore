package org.example.secondTask;

public class OrderItem {

  private final String productName;
  private final int quantity;
  private final double price;
  private final Category category;

  public OrderItem (String productName , int quantity , double price , Category category) {
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
    this.category = category;
  }

  public double getPrice () {
    return price;
  }

  public String getProductName () {
    return productName;
  }
}
