package org.pg4200.ex02;

import org.pg4200.les02.list.MyLinkedList;
import org.pg4200.les02.list.MyList;

public class MyBidirectionalLinkedList<T> implements MyList<T> {

    private class ListNode {
        T value;
        ListNode next;
        ListNode previous;
    }

    // The first element in the list
    private ListNode head;

    // The last element in the list
    private ListNode tail;

    // The number of elements contained in this container
    private int size;

    @Override
    public void delete(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            if (head.next != null) {
                //the new head
                head = head.next;
                head.previous = null;
            } else {
                //only one element in the collection, which now becomes empty
                head = null;
                tail = null;
            }
        } else if (index == size - 1) {
            tail.previous.next = null;
            tail = tail.previous;
        } else {
            ListNode target = getNode(index);

            target.previous.next = target.next;
            target.next.previous = target.previous;
        }

        size--;
    }

    private ListNode getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index <= size() / 2) {
            // start from head
            ListNode current = head;
            int counter = 0;

            while (current != null) {

                if (counter == index) {
                    return current;
                }

                current = current.next;
                counter++;
            }

        } else {
            // start from tail
            ListNode current = tail;
            int counter = 0;

            while (current != null) {
                if (counter == (size - 1) - index) {
                    return current;
                }
                current = current.previous;
                counter++;
            }
        }
        return null;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode node = new ListNode();
        node.value = value;

        if (head == null) {
            //add on empty list
            assert size == 0;
            head = node;
            tail = node;

        } else if (index == 0) {
            //add at beginning of non-empty list
            head.previous = node;
            node.next = head;
            head = node;

        } else if (index == size) {
            tail.next = node;
            node.previous = tail;
            tail = node;

        } else {
            //insertion in the middle of the list

            ListNode target = getNode(index);
            ListNode beforeTarget = target.previous;

            // from A.next - create new node (X)
            beforeTarget.next = node;

            // X.next = A
            node.previous = beforeTarget;

            // X.next = B
            node.next = target;

            // B.previous = X
            target.previous = node;
            /*
                We are in the case of ... -> A -> B -> ...
                and we want to insert X at the position of B, resulting in
                ... -> A -> X -> B -> ...
             */
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
