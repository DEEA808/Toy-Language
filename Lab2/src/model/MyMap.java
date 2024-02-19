package model;

import expression.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyMap<T, U> implements MyIMap<T, U> {
    public Map<T, U> map;

    public MyMap() {
        map = new HashMap<>();
    }

    @Override
    public void add(T key, U value) {
        map.put(key, value);

    }

    @Override
    public HashMap<T, U> display() {
        return (HashMap<T, U>) map;
    }

    @Override
    public U getByKey(T key) {
        for (T i : map.keySet()) {
          if (i == key) {
                return map.get(key);
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void update(T key, U value) {
        for (T k : map.keySet())
            if (k == key)
                map.replace(key, value);
    }

    @Override
    public boolean IsVarDefined(T key) {
        for (T k : map.keySet())
            if (k == key)
                return true;
        return false;
    }

    @Override
    public void setMap(Map<T, U> map) {
        this.map=map;
    }

    @Override
    public MyIMap<T, U> deepCopy() {
       MyIMap<T,U> newMap= new MyMap<>();
        for (T k : this.map.keySet())
            newMap.add(k,this.map.get(k));
       return newMap;

    }

    @Override
    public String toString() {
        String repres="Map[";
        Collection<T> keys=map.keySet();
        for(T key:keys){
            repres+=(key.toString()+"->"+map.get(key)+"  ");
        }
        repres+="]";
        return repres;
    }

}
