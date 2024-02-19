package model;

import expression.MyException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    public List<T> list;

    public MyList(){
        list=new ArrayList<>();

    }
    @Override
    public void add(T addValue) {
        list.add(addValue);
    }

    @Override
    public void remove(T value) {
        list.remove(value);
    }

    @Override
    public boolean isEmpty() throws MyException {
        if(list.isEmpty()) {
            throw new MyException("list is empty");
        }
        return false;
    }

    @Override
    public ArrayList<T> display() {
        //if(list.isEmpty()) {
          //  throw new MyException("list is empty");
        //}
         return (ArrayList<T>) list;
    }

    @Override
    public String toString() {
        StringBuilder repres=new StringBuilder();
        repres.append("List:\n");
        for(T elem:list){
            repres.append(elem.toString()).append("\n");
        }
        return repres.toString();
    }
}
