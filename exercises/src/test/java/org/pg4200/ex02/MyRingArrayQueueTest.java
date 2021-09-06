package org.pg4200.ex02;

import org.junit.jupiter.api.Test;
import org.pg4200.les02.queue.MyQueue;
import org.pg4200.les02.queue.MyQueueTestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyRingArrayQueueTest extends MyQueueTestTemplate {
    @Override
    protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
        return new MyRingArrayQueue<>();
    }

    @Test
    public void testFailToPeekOnEmpty() {

        assertThrows(RuntimeException.class,
                () -> queue.peek());
    }
}
