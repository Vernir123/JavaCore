package org.example.secondTask;
//Metrics:
//List of unique cities where orders came from
//Total income for all completed orders
//The most popular product by sales
//Average check for successfully delivered orders
//Customers who have more than 5 orders

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMetrics {

    // get list of unique cities from order list
    public static List<String> getUniqueCities(List<Object> orders) {
        return orders.stream()
                .map(order -> {
                    try {
                        Field f = Order.class.getDeclaredField("customer");
                        f.setAccessible(true);
                        Field c = Customer.class.getDeclaredField("city");
                        c.setAccessible(true);
                        Object customer = f.get(order);
                        return (String) c.get(customer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).distinct().toList();
    }

    //Get sum of completed (status: delivered) orders from list
    public static double getTotalIncome(List<Object> orders) {
        return orders.stream()
                .mapToDouble(order -> {
                    try {
                        Field s = Order.class.getDeclaredField("status");
                        s.setAccessible(true);
                        Object status = s.get(order);
                        if (status.toString().equals("DELIVERED")) {
                            Field i = Order.class.getDeclaredField("items");
                            i.setAccessible(true);
                            List<OrderItem> items = (List<OrderItem>) i.get(order);
                            return items.stream().mapToDouble(item -> {
                                try {
                                    Field p = OrderItem.class.getDeclaredField("price");
                                    p.setAccessible(true);
                                    Object price = p.get(item);
                                    return (Double) price;
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }).sum();
                        }
                        return 0;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).sum();
    }

    public static List<String> getPopularProduct(List<Object> orders){
        return orders.stream()
                .flatMap(order -> {
                    try {
                        Field i = Order.class.getDeclaredField("items");
                            i.setAccessible(true);
                            List<OrderItem> items = (List<OrderItem>) i.get(order);
                            return items.stream()
                                    .map(item -> {
                                try {
                                    Field p = OrderItem.class.getDeclaredField("productName");
                                    p.setAccessible(true);
                                    return (String) p.get(item);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(e-> e.getKey() + " = " + e.getValue())
                .toList();
    }

    public static Double getAverageCheck(List<Object> orders) {
        int deliveredOrders = orders.stream()
                .mapToInt(order -> {
                    try {
                        Field s = Order.class.getDeclaredField("status");
                        s.setAccessible(true);
                        Object status = s.get(order);
                        if (status.toString().equals("DELIVERED")) {
                            Field i = Order.class.getDeclaredField("items");
                            i.setAccessible(true);
                            return 1;
                        }
                        return 0;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).sum();

        return (double) OrderMetrics.getTotalIncome(orders)/deliveredOrders;
    }

    public static List<String> getActiveCustomers(List<Object> orders) {
        return orders.stream()
                .map(order -> {
                   try {
                       Field f = Order.class.getDeclaredField("customer");
                       f.setAccessible(true);
                       Field n = Customer.class.getDeclaredField("name");
                       n.setAccessible(true);
                       Object customer = f.get(order);
                       return (String) n.get(customer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 5)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(e-> e.getKey() + " = " + e.getValue())
                .toList();
    }
}
