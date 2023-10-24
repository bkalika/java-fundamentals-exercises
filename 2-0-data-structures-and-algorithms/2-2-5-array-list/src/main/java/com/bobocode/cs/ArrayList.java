package com.bobocode.cs;

import java.util.NoSuchElementException;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong><a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    private T[] array;

    private int size = 0;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity > 0) {
            this.array = (T[]) new Object[initCapacity];
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this.array = (T[]) new Object[5];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new ArrayList<>();
        for(T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (array == null) {
            array = (T[]) new Object[10];
        }
        checkArraySize(size);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = element;
                size++;
                break;
            }
        }
    }

    private void checkArraySize(int size) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[(array.length * 3) / 2 + 1];
            for (int elem = 0; elem < array.length; elem++) {
                newArray[elem] = array[elem];
            }
            array = newArray;
        }
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        checkArraySize(index);
        T existedElement = array[index];
        T nextElement;
        array[index] = element;
        for (int i = index+1; i < array.length; i++) {
            if (array[i] == null) {
                break;
            }
            nextElement = array[i];
            array[i] = existedElement;
            existedElement = nextElement;
        }
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    public T get(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[size-1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        T returnedElement = array[index];
        for (int i = index+1; i < array.length; i++) {
            array[i-1] = array[i];
        }
        size--;
        return returnedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        if (size > 0) {
            for (T t : array) {
                if (t.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return amount of saved elements
     */
    @Override
    public int size() {
//        for (T element : array) {
//            if (element != null) {
//                size++;
//            } else {
//                break;
//            }
//        }
//        return size;
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
            size--;
        }
        size = 0;
    }
}
