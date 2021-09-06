package org.pg4200.ex02;

import org.pg4200.les02.list.MyArrayList;

public class MyArrayListResizeable<T> extends MyArrayList<T> {

    @Override
    public void add(int index, T value) {
        if (index < 0) {
            //note that here "size" is a valid index
            throw new IndexOutOfBoundsException();
        }

        if (index >= data.length) {
            int newArraySize = data.length * 2;
            Object[] newData = new Object[newArraySize];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        super.add(index, value);
    }
}
