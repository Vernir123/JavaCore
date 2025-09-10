package org.example.secondTask;
//Metrics:
//List of unique cities where orders came from
//Total income for all completed orders
//The most popular product by sales
//Average check for successfully delivered orders
//Customers who have more than 5 orders

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMetrics {

  // get list of unique cities from order list
  public static List <String> getUniqueCities (List <Order> orders) {
    return orders.stream().map(order -> order.getCustomer().getCity()).distinct().toList();
  }

  //Get sum of completed (status: delivered) orders from list
  public static double getTotalIncome (List <Order> orders) {
    return orders.stream().mapToDouble(order -> {
      if (order.getStatus().name().equals("DELIVERED")) {
        return order.getItems().stream().mapToDouble(OrderItem::getPrice).sum();
      } else {
        return 0;
      }
    }).sum();
  }

  public static List <String> getPopularProduct (List <Order> orders) {
    List <String> allProduct = new ArrayList <>();
    orders.stream().map(Order::getItems)
        .forEach(list -> list.forEach(e -> allProduct.add(e.getProductName())));

    Map <String, Long> count = allProduct.stream()
        .collect(Collectors.groupingBy(name -> name , Collectors.counting()));

    return count.entrySet().stream().sorted(Map.Entry. <String, Long>comparingByValue().reversed())
        .map(k -> k.getKey() + " = " + k.getValue()).toList();


  }

  public static Double getAverageCheck (List <Order> orders) {
    int deliveredOrders = orders.stream().mapToInt(order -> {
      if (order.getStatus().name().equals("DELIVERED")) {
        return 1;
      } else {
        return 0;
      }
    }).sum();

    return OrderMetrics.getTotalIncome(orders) / deliveredOrders;
  }

  public static List <String> getActiveCustomers (List <Order> orders) {
    return orders.stream().map(order -> order.getCustomer().getName())
        .collect(Collectors.groupingBy(name -> name , Collectors.counting())).entrySet().stream()
        .filter(e -> e.getValue() > 5)
        .sorted(Map.Entry. <String, Long>comparingByValue().reversed())
        .map(e -> e.getKey() + " = " + e.getValue()).toList();
  }
}
