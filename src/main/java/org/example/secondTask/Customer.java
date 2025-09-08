package org.example.secondTask;

import java.time.LocalDateTime;

public class Customer {

  private final String customerId;
  private final String name;
  private final String email;
  private final LocalDateTime registeredAt;
  private final int age;
  private final String city;

  public Customer (String customerId , String name , String email , LocalDateTime registeredAt ,
      int age , String city) {
    this.customerId = customerId;
    this.name = name;
    this.email = email;
    this.registeredAt = registeredAt;
    this.age = age;
    this.city = city;
  }

  public String getCity () {
    return city;
  }

  public String getName () {
    return name;
  }
}
