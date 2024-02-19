package model;

import expression.MyException;
import value.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MyIHeap<K,V>{
    Integer getFreeLocation();
    Integer add(V value) ;
    HashMap<K,V> display();
    V getByKey(K key) throws MyException;
    boolean isEmpty();
    void update(K key,V value);
    boolean IsVarDefined(K key);
    void remove(K key);
    void setFreeLocation(Integer freeLocation);
    void setHeap(Map<Integer, V> heap);

}
