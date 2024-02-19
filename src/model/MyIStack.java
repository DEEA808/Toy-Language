package model;

import expression.MyException;

public interface MyIStack<T>{

    boolean isEmpty() ;
    String display();
    void  push(T addValue);

    public T pop();
}
