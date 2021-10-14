package org.pg4200.ex02;

import org.pg4200.les02.queue.MyQueue;

public class MyRingArrayQueue<T> implements MyQueue<T> {
    protected Object[] data;
    private int head = -1;
    private int tail = -1;

    public MyRingArrayQueue() {
        this(10);
    }

    public MyRingArrayQueue(int capacity) {
        data = new Object[capacity];
    }

    @Override
    public void enqueue(T value) {
        if (isEmpty()) { // Check if empty
            head = 0;
            tail = 0;

        } else if (size() < data.length) { // Check if available space
            if (tail == data.length - 1 && head != 0) { // if tail is at the end
                tail = 0;
            } else {
                tail++;
            }
        } else {
            //too many elements... let's just create a new array with double size
            Object[] tmp = new Object[data.length * 2];

            int k = data.length - head;
            for (int i = 0; i < k; i++) {
                tmp[i] = data[head + i];
            }

            for (int i = 0; i < (tail + 1); i++) {
                tmp[k + i] = data[i];
            }

            head = 0;
            tail = data.length;
            data = tmp;
        }
        data[tail] = value;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) { // Check if empty
            throw new RuntimeException("Queue is empty.");
        }

        T value = (T) data[head];

        if (head == tail) { // Check if 1 element, then empty
            head = -1;
            tail = -1;

        } else if (head == data.length - 1) {
            head = 0;
        } else {
            head++;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return (T) data[head];
    }

    @Override
    public int size() {
        if (head < 0) {
            return 0;
        } else if (head == tail) {
            return 1;
        } else if (head < tail) {
            return (tail - head) + 1;
        } else {
            int size = 0;

            size += (data.length - head);
            size += tail + 1;

            return size;
        }
    }
}
