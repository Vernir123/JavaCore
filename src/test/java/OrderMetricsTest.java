import org.example.secondTask.OrderMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OrderMetricsTest {

  // Unique Cities
  private List < Object > orderList1;

  // Total income of completed (Delivered) orders
  private List < Object > orderList2;

  // Most popular product (OrderItem)
  private List < Object > orderList3;

  //  Average check for successfully delivered orders
  private List < Object > orderList4;

  //  Customers who have >5 orders
  private List < Object > orderList5;


  private Object createCustomer ( String customId , String name , String email ,
      LocalDateTime regAt , int age , String city ) throws Exception {

    Class < ? > customerClass = Class.forName ( "org.example.secondTask.Customer" );

    Constructor < ? > customerConstructor = customerClass.getDeclaredConstructor ( );
    customerConstructor.setAccessible ( true );
    Object customer = customerConstructor.newInstance ( );

    Field idField = customerClass.getDeclaredField ( "customerId" );
    idField.setAccessible ( true );
    idField.set ( customer , customId );

    Field nameField = customerClass.getDeclaredField ( "name" );
    nameField.setAccessible ( true );
    nameField.set ( customer , name );

    Field emailField = customerClass.getDeclaredField ( "email" );
    emailField.setAccessible ( true );
    emailField.set ( customer , email );

    Field regAtField = customerClass.getDeclaredField ( "registeredAt" );
    regAtField.setAccessible ( true );
    regAtField.set ( customer , regAt );

    Field ageField = customerClass.getDeclaredField ( "age" );
    ageField.setAccessible ( true );
    ageField.set ( customer , age );

    Field cityField = customerClass.getDeclaredField ( "city" );
    cityField.setAccessible ( true );
    cityField.set ( customer , city );

    return customer;
  }

  private Object createOrderItem ( String productName , int quantity , double price ,
      Object categoryName ) throws Exception {
    Class < ? > orderItemClass = Class.forName ( "org.example.secondTask.OrderItem" );
    Constructor < ? > orderItemConstructor = orderItemClass.getDeclaredConstructor ( );
    orderItemConstructor.setAccessible ( true );
    Object orderItem = orderItemConstructor.newInstance ( );

    Field productNameField = orderItemClass.getDeclaredField ( "productName" );
    productNameField.setAccessible ( true );
    productNameField.set ( orderItem , productName );

    Field quantityField = orderItemClass.getDeclaredField ( "quantity" );
    quantityField.setAccessible ( true );
    quantityField.set ( orderItem , quantity );

    Field priceField = orderItemClass.getDeclaredField ( "price" );
    priceField.setAccessible ( true );
    priceField.set ( orderItem , price );

    Field categoryField = orderItemClass.getDeclaredField ( "category" );
    categoryField.setAccessible ( true );
    categoryField.set ( orderItem , categoryName );

    return orderItem;
  }

  private Object createOrder ( String id , LocalDateTime date , Object customer ,
      List < Object > items , Object status ) throws Exception {
    Class < ? > orderClass = Class.forName ( "org.example.secondTask.Order" );
    Constructor < ? > orderConstructor = orderClass.getDeclaredConstructor ( );
    orderConstructor.setAccessible ( true );
    Object order = orderConstructor.newInstance ( );

    Field idField = orderClass.getDeclaredField ( "orderId" );
    idField.setAccessible ( true );
    idField.set ( order , id );

    Field dateField = orderClass.getDeclaredField ( "orderDate" );
    dateField.setAccessible ( true );
    dateField.set ( order , date );

    Field customerField = orderClass.getDeclaredField ( "customer" );
    customerField.setAccessible ( true );
    customerField.set ( order , customer );

    Field itemsField = orderClass.getDeclaredField ( "items" );
    itemsField.setAccessible ( true );
    itemsField.set ( order , items );

    Field statusField = orderClass.getDeclaredField ( "status" );
    statusField.setAccessible ( true );
    statusField.set ( order , status );

    return order;
  }

  @BeforeEach
  void setUp ( ) throws Exception {
    //ELECTRONICS, CLOTHING, BOOKS, HOME, BEAUTY, TOYS
    Class < ? > categoryEnumClass = Class.forName ( "org.example.secondTask.Category" );
    Object[] categories = categoryEnumClass.getEnumConstants ( );

    //NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    Class < ? > orderStatusEnumClass = Class.forName ( "org.example.secondTask.OrderStatus" );
    Object[] statuses = orderStatusEnumClass.getEnumConstants ( );

    Object orderItem1 = createOrderItem ( "Nut" , 7 , 5.5 , categories[0] );
    Object orderItem2 = createOrderItem ( "Hat" , 3 , 20.19 , categories[1] );
    Object orderItem3 = createOrderItem ( "Mein K." , 1 , 138.1 , categories[2] );
    Object orderItem4 = createOrderItem ( "Wall" , 5 , 1499.99 , categories[3] );
    Object orderItem5 = createOrderItem ( "Vaseline" , 1 , 12.49 , categories[4] );
    Object orderItem6 = createOrderItem ( "Handcuffs" , 2 , 49.99 , categories[5] );

    Object customer1 = createCustomer ( "1" , "Alex" , "snap@gmail.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "London" );
    Object customer2 = createCustomer ( "2" , "Victor" , "LoseEverything@mail.ru" ,
        LocalDate.now ( ).minusYears ( 4 ).atStartOfDay ( ) , 37 , "Saratov" );
    Object customer3 = createCustomer ( "3" , "Grzegorz" , "Bzhenzhishchikevich@outlook.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 12 , "Chrzaszczyzewoszyce" );
    Object customer4 = createCustomer ( "4" , "Violet" , "free4all@gmail.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "London" );
    Object customer5 = createCustomer ( "5" , "Egor" , "work@mail.ru" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "Saratov" );
    Object customer6 = createCustomer ( "6" , "Mary" , "fly@live.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "London" );
    Object customer7 = createCustomer ( "7" , "Katy" , "KillJoy@hotmail.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "New-York" );
    Object customer8 = createCustomer ( "8" , "Tom" , "AndJerry@gmail.com" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "Minsk" );
    Object customer9 = createCustomer ( "9" , "Mike" , "Vasovski@mail.ru" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "Moscow" );
    Object customer10 = createCustomer ( "10" , "Alexander" , "IllShowYou@mail.ru" ,
        LocalDate.now ( ).atStartOfDay ( ) , 8 , "Minsk" );

    Object order1 = createOrder ( "1" , LocalDateTime.now ( ) , customer1 ,
        List.of ( orderItem1 , orderItem2 , orderItem3 , orderItem4 , orderItem5 , orderItem6 ) ,
        statuses[2] );
    Object order2 = createOrder ( "2" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem1 , orderItem2 ) , statuses[3] );
    Object order3 = createOrder ( "3" , LocalDateTime.now ( ) , customer1 , List.of ( orderItem4 ) ,
        statuses[4] );
    Object order4 = createOrder ( "4" , LocalDateTime.now ( ) , customer1 , List.of ( orderItem3 ) ,
        statuses[2] );
    Object order5 = createOrder ( "5" , LocalDateTime.now ( ) , customer1 , List.of ( orderItem4 ) ,
        statuses[3] );
    Object order6 = createOrder ( "6" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem1 , orderItem2 ) , statuses[2] );
    Object order7 = createOrder ( "7" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem1 , orderItem2 ) , statuses[1] );
    Object order8 = createOrder ( "8" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem1 , orderItem2 ) , statuses[0] );
    Object order9 = createOrder ( "9" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem1 , orderItem2 ) , statuses[0] );
    Object order10 = createOrder ( "10" , LocalDateTime.now ( ) , customer3 ,
        List.of ( orderItem5 ) , statuses[4] );
    Object order11 = createOrder ( "11" , LocalDateTime.now ( ) , customer3 ,
        List.of ( orderItem3 ) , statuses[2] );
    Object order12 = createOrder ( "12" , LocalDateTime.now ( ) , customer1 ,
        List.of ( orderItem5 ) , statuses[3] );
    Object order13 = createOrder ( "13" , LocalDateTime.now ( ) , customer4 ,
        List.of ( orderItem2 ) , statuses[4] );
    Object order14 = createOrder ( "14" , LocalDateTime.now ( ) , customer5 ,
        List.of ( orderItem3 ) , statuses[2] );
    Object order15 = createOrder ( "15" , LocalDateTime.now ( ) , customer6 ,
        List.of ( orderItem4 ) , statuses[3] );
    Object order16 = createOrder ( "16" , LocalDateTime.now ( ) , customer7 ,
        List.of ( orderItem2 ) , statuses[4] );
    Object order17 = createOrder ( "17" , LocalDateTime.now ( ) , customer8 ,
        List.of ( orderItem3 ) , statuses[2] );
    Object order18 = createOrder ( "18" , LocalDateTime.now ( ) , customer9 ,
        List.of ( orderItem4 ) , statuses[3] );
    Object order19 = createOrder ( "19" , LocalDateTime.now ( ) , customer10 ,
        List.of ( orderItem2 ) , statuses[4] );
    Object order20 = createOrder ( "20" , LocalDateTime.now ( ) , customer1 ,
        List.of ( orderItem3 ) , statuses[2] );
    Object order21 = createOrder ( "21" , LocalDateTime.now ( ) , customer1 ,
        List.of ( orderItem4 ) , statuses[3] );
    Object order22 = createOrder ( "22" , LocalDateTime.now ( ) , customer1 ,
        List.of ( orderItem2 ) , statuses[3] );
    Object order23 = createOrder ( "23" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem3 ) , statuses[3] );
    Object order24 = createOrder ( "24" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem4 ) , statuses[4] );
    Object order25 = createOrder ( "25" , LocalDateTime.now ( ) , customer2 ,
        List.of ( orderItem2 ) , statuses[4] );
    Object order26 = createOrder ( "26" , LocalDateTime.now ( ) , customer3 ,
        List.of ( orderItem3 ) , statuses[4] );
    Object order27 = createOrder ( "27" , LocalDateTime.now ( ) , customer3 ,
        List.of ( orderItem4 ) , statuses[4] );
    Object order28 = createOrder ( "28" , LocalDateTime.now ( ) , customer3 ,
        List.of ( orderItem2 ) , statuses[4] );
    Object order29 = createOrder ( "29" , LocalDateTime.now ( ) , customer4 ,
        List.of ( orderItem3 ) , statuses[4] );
    Object order30 = createOrder ( "30" , LocalDateTime.now ( ) , customer4 ,
        List.of ( orderItem4 ) , statuses[4] );

    //London, Saratov, London, Chrzaszczyzewoszyce, London, Saratov, Minsk
    orderList1 = List.of ( order1 , order2 , order3 , order10 , order13 , order14 , order19 );

    // completed: 2, 5, 12
    // costs: 5.5+20.19, 1499.99, 12.49
    // sum: 1538,17
    orderList2 = List.of ( order1 , order2 , order3 , order5 , order6 , order7 , order8 , order12 );

    // 6, 6, 3, 2, 2, 1
    orderList3 = List.of ( order1 , order2 , order3 , order4 , order5 , order6 , order7 , order8 ,
        order9 , order10 , order11 );

    // average check for delivered(7) orders =  865.4757142857143; full cost = 6058.33
    List < Object > extraList = List.of ( order12 , order13 , order14 , order15 , order16 ,
        order17 , order18 , order19 , order20 , order21 , order22 );
    orderList4 = Stream.concat ( orderList3.stream ( ) , extraList.stream ( ) )
        .collect ( Collectors.toList ( ) );

    // customer1 = 8, customer2 = 8; customer3 = 5
    List < Object > extraList2 = List.of ( order23 , order24 , order25 , order26 , order27 ,
        order28 , order29 , order30 );
    orderList5 = Stream.concat ( orderList4.stream ( ) , extraList2.stream ( ) )
        .collect ( Collectors.toList ( ) );


  }

  @Test
  void metricsUniqueCitiesTest ( ) {

    List < String > uniqueCities = OrderMetrics.getUniqueCities ( orderList1 );

    assertEquals ( List.of ( "London" , "Saratov" , "Chrzaszczyzewoszyce" , "Minsk" ) ,
        uniqueCities );
  }

  @Test
  void metricsTotalIncomeTest ( ) {
    Double totalIncome = OrderMetrics.getTotalIncome ( orderList2 );

    assertEquals ( Double.valueOf ( 1538.17 ) , totalIncome );
  }

  @Test
  void metricsMostPopularTest ( ) {
    List < String > mostPopular = OrderMetrics.getPopularProduct ( orderList3 );
    List < String > result = List.of ( "Hat = 6" , "Nut = 6" , "Wall = 3" , "Mein K. = 3" ,
        "Vaseline = 2" , "Handcuffs = 1" );
    assertEquals ( result , mostPopular );
  }

  @Test
  void metricsAverageCheckTest ( ) {
    Double averageCheck = OrderMetrics.getAverageCheck ( orderList4 );
    assertEquals ( Double.valueOf ( 865.4757142857143 ) , averageCheck );
  }

  @Test
  void metricsActiveCustomersTest ( ) {
    List < String > activeCustomers = OrderMetrics.getActiveCustomers ( orderList5 );
    assertEquals ( List.of ( "Victor = 8" , "Alex = 8" ) , activeCustomers );
  }

}
