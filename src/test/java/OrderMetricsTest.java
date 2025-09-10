import org.example.secondTask.Category;
import org.example.secondTask.Customer;
import org.example.secondTask.Order;
import org.example.secondTask.OrderItem;
import org.example.secondTask.OrderMetrics;
import org.example.secondTask.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMetricsTest {

  private List <Order> orderList1, orderList2, orderList3, orderList4, orderList5;


  @BeforeEach
  void setUp () {

    OrderItem orderItem1 = new OrderItem("Nut" , 7 , 5.5 , Category.ELECTRONICS);
    OrderItem orderItem2 = new OrderItem("Hat" , 3 , 20.19 , Category.CLOTHING);
    OrderItem orderItem3 = new OrderItem("Mein K." , 1 , 138.1 , Category.BOOKS);
    OrderItem orderItem4 = new OrderItem("Wall" , 5 , 1499.99 , Category.HOME);
    OrderItem orderItem5 = new OrderItem("Vaseline" , 1 , 12.49 , Category.BEAUTY);
    OrderItem orderItem6 = new OrderItem("Handcuffs" , 2 , 49.99 , Category.TOYS);

    Customer customer1 = new Customer("1" , "Alex" , "snap@gmail.com" ,
        LocalDate.now().atStartOfDay() , 8 , "London");
    Customer customer2 = new Customer("2" , "Victor" , "LoseEverything@mail.ru" ,
        LocalDate.now().minusYears(4).atStartOfDay() , 37 , "Saratov");
    Customer customer3 = new Customer("3" , "Grzegorz" , "Bzhenzhishchikevich@outlook.com" ,
        LocalDate.now().atStartOfDay() , 12 , "Chrzaszczyzewoszyce");
    Customer customer4 = new Customer("4" , "Violet" , "free4all@gmail.com" ,
        LocalDate.now().atStartOfDay() , 8 , "London");
    Customer customer5 = new Customer("5" , "Egor" , "work@mail.ru" ,
        LocalDate.now().atStartOfDay() , 8 , "Saratov");
    Customer customer6 = new Customer("6" , "Mary" , "fly@live.com" ,
        LocalDate.now().atStartOfDay() , 8 , "London");
    Customer customer7 = new Customer("7" , "Katy" , "KillJoy@hotmail.com" ,
        LocalDate.now().atStartOfDay() , 8 , "New-York");
    Customer customer8 = new Customer("8" , "Tom" , "AndJerry@gmail.com" ,
        LocalDate.now().atStartOfDay() , 8 , "Minsk");
    Customer customer9 = new Customer("9" , "Mike" , "Vasovski@mail.ru" ,
        LocalDate.now().atStartOfDay() , 8 , "Moscow");
    Customer customer10 = new Customer("10" , "Alexander" , "IllShowYou@mail.ru" ,
        LocalDate.now().atStartOfDay() , 8 , "Minsk");

    Order order1 = new Order("1" , LocalDateTime.now() , customer1 ,
        List.of(orderItem1 , orderItem2 , orderItem3 , orderItem4 , orderItem5 , orderItem6) ,
        OrderStatus.SHIPPED);
    Order order2 = new Order("2" , LocalDateTime.now() , customer2 ,
        List.of(orderItem1 , orderItem2) , OrderStatus.DELIVERED);
    Order order3 = new Order("3" , LocalDateTime.now() , customer1 , List.of(orderItem4) ,
        OrderStatus.CANCELLED);
    Order order4 = new Order("4" , LocalDateTime.now() , customer1 , List.of(orderItem3) ,
        OrderStatus.SHIPPED);
    Order order5 = new Order("5" , LocalDateTime.now() , customer1 , List.of(orderItem4) ,
        OrderStatus.DELIVERED);
    Order order6 = new Order("6" , LocalDateTime.now() , customer2 ,
        List.of(orderItem1 , orderItem2) , OrderStatus.SHIPPED);
    Order order7 = new Order("7" , LocalDateTime.now() , customer2 ,
        List.of(orderItem1 , orderItem2) , OrderStatus.PROCESSING);
    Order order8 = new Order("8" , LocalDateTime.now() , customer2 ,
        List.of(orderItem1 , orderItem2) , OrderStatus.NEW);
    Order order9 = new Order("9" , LocalDateTime.now() , customer2 ,
        List.of(orderItem1 , orderItem2) , OrderStatus.NEW);
    Order order10 = new Order("10" , LocalDateTime.now() , customer3 , List.of(orderItem5) ,
        OrderStatus.CANCELLED);
    Order order11 = new Order("11" , LocalDateTime.now() , customer3 , List.of(orderItem3) ,
        OrderStatus.SHIPPED);
    Order order12 = new Order("12" , LocalDateTime.now() , customer1 , List.of(orderItem5) ,
        OrderStatus.DELIVERED);
    Order order13 = new Order("13" , LocalDateTime.now() , customer4 , List.of(orderItem2) ,
        OrderStatus.CANCELLED);
    Order order14 = new Order("14" , LocalDateTime.now() , customer5 , List.of(orderItem3) ,
        OrderStatus.SHIPPED);
    Order order15 = new Order("15" , LocalDateTime.now() , customer6 , List.of(orderItem4) ,
        OrderStatus.DELIVERED);
    Order order16 = new Order("16" , LocalDateTime.now() , customer7 , List.of(orderItem2) ,
        OrderStatus.CANCELLED);
    Order order17 = new Order("17" , LocalDateTime.now() , customer8 , List.of(orderItem3) ,
        OrderStatus.SHIPPED);
    Order order18 = new Order("18" , LocalDateTime.now() , customer9 , List.of(orderItem4) ,
        OrderStatus.DELIVERED);
    Order order19 = new Order("19" , LocalDateTime.now() , customer10 , List.of(orderItem2) ,
        OrderStatus.CANCELLED);
    Order order20 = new Order("20" , LocalDateTime.now() , customer1 , List.of(orderItem3) ,
        OrderStatus.SHIPPED);
    Order order21 = new Order("21" , LocalDateTime.now() , customer1 , List.of(orderItem4) ,
        OrderStatus.DELIVERED);
    Order order22 = new Order("22" , LocalDateTime.now() , customer1 , List.of(orderItem2) ,
        OrderStatus.DELIVERED);
    Order order23 = new Order("23" , LocalDateTime.now() , customer2 , List.of(orderItem3) ,
        OrderStatus.DELIVERED);
    Order order24 = new Order("24" , LocalDateTime.now() , customer2 , List.of(orderItem4) ,
        OrderStatus.CANCELLED);
    Order order25 = new Order("25" , LocalDateTime.now() , customer2 , List.of(orderItem2) ,
        OrderStatus.CANCELLED);
    Order order26 = new Order("26" , LocalDateTime.now() , customer3 , List.of(orderItem3) ,
        OrderStatus.CANCELLED);
    Order order27 = new Order("27" , LocalDateTime.now() , customer3 , List.of(orderItem4) ,
        OrderStatus.CANCELLED);
    Order order28 = new Order("28" , LocalDateTime.now() , customer3 , List.of(orderItem2) ,
        OrderStatus.CANCELLED);
    Order order29 = new Order("29" , LocalDateTime.now() , customer4 , List.of(orderItem3) ,
        OrderStatus.CANCELLED);
    Order order30 = new Order("30" , LocalDateTime.now() , customer4 , List.of(orderItem4) ,
        OrderStatus.CANCELLED);

    orderList1 = List.of(order1 , order2 , order3 , order10 , order13 , order14 , order19);

    orderList2 = List.of(order1 , order2 , order3 , order5 , order6 , order7 , order8 , order12);

    orderList3 = List.of(order1 , order2 , order3 , order4 , order5 , order6 , order7 , order8 ,
        order9 , order10 , order11);

    List <Order> extraList = List.of(order12 , order13 , order14 , order15 , order16 , order17 ,
        order18 , order19 , order20 , order21 , order22);
    orderList4 = Stream.concat(orderList3.stream() , extraList.stream()).toList();

    List <Order> extraList2 = List.of(order23 , order24 , order25 , order26 , order27 , order28 ,
        order29 , order30);
    orderList5 = Stream.concat(orderList4.stream() , extraList2.stream()).toList();


  }

  @Test
  @DisplayName("Unique cities")
  void metricsUniqueCitiesTest () {

    List <String> uniqueCities = OrderMetrics.getUniqueCities(orderList1);

    assertEquals(List.of("London" , "Saratov" , "Chrzaszczyzewoszyce" , "Minsk") , uniqueCities);
  }

  @Test
  @DisplayName("Total income")
  void metricsTotalIncomeTest () {
    Double totalIncome = OrderMetrics.getTotalIncome(orderList2);

    assertEquals(Double.valueOf(1538.17) , totalIncome);
  }

  @Test
  @DisplayName("Most popular")
  void metricsMostPopularTest () {
    List <String> mostPopular = OrderMetrics.getPopularProduct(orderList3);
    List <String> result = List.of("Hat = 6" , "Nut = 6" , "Wall = 3" , "Mein K. = 3" ,
        "Vaseline = 2" , "Handcuffs = 1");
    assertEquals(result , mostPopular);
  }

  @Test
  @DisplayName("Average check")
  void metricsAverageCheckTest () {
    Double averageCheck = OrderMetrics.getAverageCheck(orderList4);
    assertEquals(Double.valueOf(865.4757142857143) , averageCheck);
  }

  @Test
  @DisplayName("Active customers")
  void metricsActiveCustomersTest () {
    List <String> activeCustomers = OrderMetrics.getActiveCustomers(orderList5);
    assertEquals(List.of("Victor = 8" , "Alex = 8") , activeCustomers);
  }

}
