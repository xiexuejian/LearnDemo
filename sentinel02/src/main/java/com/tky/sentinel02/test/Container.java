package com.tky.sentinel02.test;

public interface Container<E>{
    void add(E e);
    default void insert(int index,E value){
        throw new UnsupportedOperationException();
    }
    void delete(E e);
    default void delete(int index){
        throw new UnsupportedOperationException();
    }
    default E update(int index, E value){
        throw new UnsupportedOperationException();
    }
    void update(E old, E value);
    boolean contains(E e);
    default int indexOf(E e){
        throw new UnsupportedOperationException();
    }
    default E get(int index){
        throw new UnsupportedOperationException();
    }
    Object[] getAll();
    int size();
}
