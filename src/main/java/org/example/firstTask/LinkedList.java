package org.example.firstTask;

public class LinkedList<T>{
    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        if (tail == null) tail = head;
        size++;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("invalid index");
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public T getFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        return head.value;
    }

    public T getLast() {
        if (tail == null) throw new IllegalStateException("List is empty");
        return tail.value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("invalid index");
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public T removeFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        T value = head.value;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return value;
    }
    public T removeLast() {
        if (tail == null) throw new IllegalStateException("List is empty");
        if (size == 1) return removeFirst();

        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T value = tail.value;
        tail = current;
        tail.next = null;
        size--;
        return value;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("invalid index");
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        T value = current.next.value;
        current.next = current.next.next;
        size--;
        return value;
    }


}
