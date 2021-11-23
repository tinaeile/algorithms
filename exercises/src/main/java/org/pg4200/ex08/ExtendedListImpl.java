package org.pg4200.ex08;

import org.pg4200.les02.list.MyArrayList;
import org.pg4200.les06.set.MySet;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExtendedListImpl<T> extends MyArrayList<T> implements ExtendedList<T> {

    @Override
    public ExtendedList<T> filter(Predicate<T> predicate) {

        ExtendedList<T> other = new ExtendedListImpl<>();

        for (int i = 0; i < this.size; i++) {
            T value = this.get(i);
            if (predicate.test(value)) {
                other.add(value);
            }
        }
        return other;
    }

    @Override
    public <R> ExtendedList<R> map(Function<T, R> mapper) {

        return null;
    }

    @Override
    public <R> ExtendedList<R> flatMap(Function<T, ExtendedList<R>> mapper) {
        return null;
    }

    @Override
    public void forEach(Consumer<T> action) {
    }

    @Override
    public MySet<T> toSet() {
        return null;
    }
}
