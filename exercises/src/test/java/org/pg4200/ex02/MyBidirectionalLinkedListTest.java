package org.pg4200.ex02;

import org.pg4200.les02.list.MyList;
import org.pg4200.les02.list.MyListTestTemplate;

public class MyBidirectionalLinkedListTest extends MyListTestTemplate {
    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyBidirectionalLinkedList<T>();
    }
}
