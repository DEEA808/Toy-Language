package model;

import expression.MyException;

import java.util.ArrayList;

public interface MyIList<T>{
    void add(T addValue);
    void remove(T value);
    boolean isEmpty() throws MyException;
    ArrayList<T> display();
}
