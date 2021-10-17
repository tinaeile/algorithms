package org.pg4200.ex02;

import org.pg4200.les02.list.MyList;

public class MyMiddleBidirectionalLinkedList<T> implements MyList<T> {
    private class ListNode {
        T value;
        ListNode next;
        ListNode previous;
    }

    private ListNode head;
    private ListNode middle;
    private ListNode tail;
    private int size;

    private int expectedIndexOfMiddle() {
        if (isEmpty()) {
            return -1;
        }

        return (int) (Math.ceil(size() / 2.0) - 1);
    }

    private void updateMiddle(int previousIndex) {

        int current = expectedIndexOfMiddle();
        if (current == previousIndex) {
            //nothing to do
            return;
        }

        if (previousIndex < 0) {
            //from empty to 1 element
            assert size() == 1;
            middle = head;
        } else if (current < 0) {
            //from 1 element to empty
            assert size() == 0;
            middle = null;
        } else if (current < previousIndex) {
            //move backwards
            middle = middle.previous;
        } else {
            //move forward
            middle = middle.next;
        }
    }


    private ListNode getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        ListNode current = null;
        int counter = -1;
        boolean forwards = false;

        if (index <= size() * 0.25) {
            // start from head
            current = head;
            counter = 0;
            forwards = true;

        } else if (index > size() * 0.25 && index < size() * 0.75) {
            // start from middle - backwards or forwards
            current = middle;
            counter = expectedIndexOfMiddle();
            forwards = index >= size() * 0.5;
        } else {
            // start from tail
            current = tail;
            counter = size - 1;
            forwards = false;
        }

        if (forwards) {
            while (counter <= index) {

                if (counter == index) {
                    return current;
                }

                current = current.next;
                counter++;
            }
        } else {
            // Backwards
            while (counter >= index) {

                if (counter == index) {
                    return current;
                }

                current = current.previous;
                counter--;
            }
        }
        return null;
    }

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

        int previous = expectedIndexOfMiddle();
        if (index <= previous) {
            previous--;
        }
        size--;
        updateMiddle(previous);
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

        int previous = expectedIndexOfMiddle();
        if (index <= previous) {
            previous++;
        }
        size++;
        updateMiddle(previous);
    }

    @Override
    public int size() {
        return size;
    }
}
