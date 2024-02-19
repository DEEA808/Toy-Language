package model;

import expression.MyException;
import value.RefValue;
import value.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyHeap<V> implements MyIHeap<Integer, V> {
    public Map<Integer, V> heap;
    private Integer freeLocation;

    public void setHeap(Map<Integer, V> heap) {
        this.heap = heap;
    }

    public MyHeap() {
        heap = new HashMap<>();
        freeLocation = 1;
    }

    public Integer getFreeLocation() {
        return freeLocation;
    }

    @Override
    public Integer add(V value) {
        heap.put(freeLocation, value);
        freeLocation++;
        return freeLocation - 1;
    }

    @Override
    public HashMap<Integer, V> display() {
        return (HashMap<Integer, V>) heap;
    }

    @Override
    public V getByKey(Integer key) throws MyException {

        return heap.get(key);

    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void update(Integer key, V value) {

        heap.replace(key, value);
    }

    @Override
    public boolean IsVarDefined(Integer key) {
        return heap.containsKey(key);
    }

    public void setFreeLocation(Integer freeLocation) {
        this.freeLocation = freeLocation;
    }

    @Override
    public void remove(Integer key) {
        this.heap.remove(key);
    }

    @Override
    public String toString() {
        String repres = "Heap:[";
        Collection<Integer> keys = heap.keySet();
        for (Integer key : keys) {
            repres += (key.toString() + "->" + heap.get(key) + "  ");
        }
        repres += "]";
        return repres;
    }


}
