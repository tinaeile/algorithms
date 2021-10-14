package org.pg4200.ex02;

import org.pg4200.les02.list.MyArrayList;

public class MyArrayListResizeable<T> extends MyArrayList<T> {

    @Override
    public void add(int index, T value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index >= data.length) {
            int newArraySize = data.length * 2;
            Object[] newData = new Object[newArraySize];

            if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        super.add(index, value);
    }
}
