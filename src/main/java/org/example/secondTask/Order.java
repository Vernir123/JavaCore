package org.example.secondTask;

import java.time.LocalDateTime;
import java.util.List;

class Order {

  private String orderId;
  private LocalDateTime orderDate;
  private Customer customer;
  private List <OrderItem> items;
  private OrderStatus status;
}


