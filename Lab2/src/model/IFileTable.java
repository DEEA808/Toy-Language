package model;

import expression.MyException;

import java.util.HashMap;

public interface IFileTable<T,U> {
    void add(T key,U value) ;
    HashMap<T,U> display();
    U getByKey(T key) throws MyException;
    boolean isEmpty();
    void update(T key,U value);
    boolean IsVarDefined(T key);
    void remove(T key);
}
