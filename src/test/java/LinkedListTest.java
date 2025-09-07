import org.example.firstTask.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

  private LinkedList < Integer > list;

  @BeforeEach
  void setUp () {
    list = new LinkedList <> ( );
  }

  @Test
  void testSize () {
    list.addLast ( 10 );
    list.addLast ( 20 );
    list.addLast ( 30 );
    assertEquals ( 3 , list.size ( ) );
  }

  @Test
  void testAddFirst () {
    list.addFirst ( 10 );
    assertEquals ( 1 , list.size ( ) );
    assertEquals ( 10 , list.getFirst ( ) );
  }

  @Test
  void testAddLast () {
    list.addLast ( 20 );
    list.addLast ( null );
    assertEquals ( 2 , list.size ( ) );
    assertNull ( list.getLast ( ) );
  }

  @Test
  void testAddByIndex () {
    list.addFirst ( 10 );
    list.addLast ( 30 );
    list.add ( 1 , 20 );
    assertEquals ( 3 , list.size ( ) );
    assertEquals ( 20 , list.get ( 1 ) );
  }

  @Test
  void testGetFirstAndLast () {
    list.addLast ( 5 );
    list.addLast ( 15 );
    assertEquals ( 5 , list.getFirst ( ) );
    assertEquals ( 15 , list.getLast ( ) );
  }

  @Test
  void testGetByIndex () {
    list.addLast ( 100 );
    list.addLast ( 200 );
    assertEquals ( 200 , list.get ( 1 ) );
  }

  @Test
  void testRemoveFirst () {
    list.addLast ( 1 );
    list.addLast ( 2 );
    int removed = list.removeFirst ( );
    assertEquals ( 1 , removed );
    assertEquals ( 1 , list.size ( ) );
    assertEquals ( 2 , list.getFirst ( ) );
  }

  @Test
  void testRemoveLast () {
    list.addLast ( 1 );
    list.addLast ( 2 );
    int removed = list.removeLast ( );
    assertEquals ( 2 , removed );
    assertEquals ( 1 , list.size ( ) );
    assertEquals ( 1 , list.getLast ( ) );
  }

  @Test
  void testRemoveByIndex () {
    list.addLast ( 10 );
    list.addLast ( 20 );
    list.addLast ( 30 );
    int removed = list.remove ( 1 );
    assertEquals ( 20 , removed );
    assertEquals ( 2 , list.size ( ) );
    assertEquals ( 30 , list.get ( 1 ) );
  }

  @Test
  void testIndexOutOfBounds () {
    assertThrows ( IndexOutOfBoundsException.class , () -> list.get ( 0 ) );
    assertThrows ( IndexOutOfBoundsException.class , () -> list.add ( 1 , 5 ) );
    assertThrows ( IndexOutOfBoundsException.class , () -> list.remove ( 0 ) );
  }

  @Test
  void testEmptyListExceptions () {
    assertThrows ( IllegalStateException.class , () -> list.getFirst ( ) );
    assertThrows ( IllegalStateException.class , () -> list.getLast ( ) );
    assertThrows ( IllegalStateException.class , () -> list.removeFirst ( ) );
    assertThrows ( IllegalStateException.class , () -> list.removeLast ( ) );
  }
}
