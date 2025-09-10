package org.example.firstTask;

public interface LinkedListInterface<T> {

  int size ();

  void add (int index , T data);

  void addFirst (T data);

  void addLast (T data);

  T getFirst ();

  T getLast ();

  T get (int index);

  T removeFirst ();

  T removeLast ();

  T remove (int index);

  String toString ();

}
