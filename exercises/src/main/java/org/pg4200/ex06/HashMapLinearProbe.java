package org.pg4200.ex06;
import org.pg4200.les06.hash.MyHashMap;

import java.lang.reflect.Array;

public class HashMapLinearProbe<K, V> implements MyHashMap<K, V> {

    // Important this is a prime number.
    private final int M = 997;

    private class Entry {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] data = (Entry[]) Array.newInstance(Entry.class, M);

    private int size = 0;

    private boolean isPresentButNotMatching(int i, K key) {
        // if index < array -> within array && index != null -> space empty or occupied && key != key
        // Takes 2 params: position in data and key.
        // Checks if key already exists in array at each index. Returns true if key doesnt match
        return i < data.length && data[i] != null && !key.equals(data[i].key);
    }

    private int findKey(int i, K key) {

        int k = i;

        // check if key is present in array
        while (isPresentButNotMatching(k, key) && k < data.length) {
            k++;
        }

        /* Goes through isPresentButNotMatching and did not reach the end, ie. k != data.length
         * and found either empty or same key
         */
        if (k < data.length) {
            if (data[k] == null) {
                // if found empty slot and not matching key, return -1
                return -1;
            }
            // found matching key and not empty slot. return its index in arr.
            assert key.equals(data[k].key);
            return k;
        }

        /* isPresentButNotMatching didn't find empty slot or matching key,
         * ie. hit end of array
         */

        // To ensure we check the whole array
        k = 0;

        // Search from start to i, ie what we've already checked
        while (isPresentButNotMatching(k, key) && k < i) {
            k++;
        }

        if (k < i && data[k] != null) {
            // matching key
            assert key.equals(data[k].key);
            return k;
        } else {
            // empty slot or end of remaining array, ie array is full.
            return -1;
        }
    }

    private int findEmpty(int i) {
        // starts where findKey ends
        // to check what we have is empty slot, not full array.
        int k = i;

        // runs until end of array
        // !isMissing = true => k++
        while (!isMissing(k) && k < data.length) {
            k++;
        }
        // exit while loop if data[i] == null || data[i].key = null

        if (k < data.length) {
            assert isMissing(k);
            return k;
        }

        // reset
        k = 0;

        // Check first part
        while (!isMissing(k) && k < i) {
            k++;
        }

        // If found empty slot return k, otherwise -1
        if (k < i) {
            return k;
        } else {
            return -1;
        }
    }

    private boolean isMissing(int i) {
        // out of bounds OR array is empty at index position OR key at index position is null
        return i == data.length || data[i] == null || data[i].key == null;
    }

    @Override
    public void put(K key, V value) {
        int i = index(key);

        int position = findKey(i, key);
        if (position < 0) {
            position = findEmpty(i);
        }

        // if findEmpty returns -1 position will be less than 0
        // ie, array is fulll
        if (position < 0) {
            throw new IllegalArgumentException("Map is full");
        }

        // if theres an available slot, this code will run
        // Check if slot is empty or if it has key = null and value = something.
        if (data[position] == null) {

            // make new entry in empty slot
            data[position] = new Entry(key, value);
            size++;
        } else {
            // set old values to new
            data[position].key = key;
            data[position].value = value;
        }
    }

    private int index(K key) {

        /*
            The hash is an integer, so in the range -2B, +2B
            But here we want to map into 0..M-1, ie a valid
            index on the array.

            So, first step is to make sure the hash is positive,
            by throwing away its first leftmost bit (which define
            the sign of the number).

            How to do it? By using the mask 0x7f_ff_ff_ff
            But what does it mean?

            An F is the value 15 in hexadecimal format, which in
            binary is 1111 (ie, 2^3 + 2^2 + 2^1 + 2^0 = 8 + 4 + 2 + 1 = 15)
            So an "& F" means take all the bits at that position.
            However, if we want to skip a first bit in a &, we
            need the following mask in binary

            0111

            as an & with 0 is always 0, ie
            0 & 0 = 0
            0 & 1 = 0
            1 & 0 = 0
            1 & 1 = 1

            in hexadecimal (and decimal as well), the binary 0111 is
            a 7 (ie 2^2 + 2^1 + 2^0 = 4 + 2 + 1)
         */
        int positiveHash = key.hashCode() & 0x7f_ff_ff_ff;

        /*
            The result of %M is a value in 0..M-1
         */

        return positiveHash % M;
    }

    @Override
    public void delete(K key) {

        int i = index(key);

        int position = findKey(i, key);

        // if findKey returns -1 cause key you want to delete dont exist,
        // do nothing
        if (position < 0) {
            return;
        }

        // delete key and value if key exists
        // but not entry ie data[position]
        if (data[position] != null) {
            data[position].key = null;
            data[position].value = null;
            size--;
        }
    }

    @Override
    public V get(K key) {
        int i = index(key);

        int position = findKey(i, key);
        if (position >= 0 && !isMissing(position)) {
            return data[position].value;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
