package model;

import expression.MyException;

import java.util.HashMap;
import java.util.Map;

public interface MyIMap<T,U> {
    void add(T key,U value) ;
    HashMap<T,U> display();
    U getByKey(T key) throws MyException;
    boolean isEmpty();
    void update(T key,U value);
    boolean IsVarDefined(T key);
    public void setMap(Map<T, U> map);
    public MyIMap<T,U> deepCopy();

}
