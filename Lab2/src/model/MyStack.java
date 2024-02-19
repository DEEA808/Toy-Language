package model;

import expression.MyException;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    public Stack<T> stack;
    public MyStack(){
        stack=new Stack<>();
    }

    @Override
    public boolean isEmpty()  {

        if (stack.isEmpty())
                return true;

        return false;
    }

    @Override
    public String display() {
        if(stack.isEmpty())
            throw new EmptyStackException();
        return stack.toString();
    }

    @Override
    public void  push(T addValue) {
         stack.push(addValue);
    }

    @Override
    public T pop() {
        if(stack.isEmpty())
            throw new EmptyStackException();
        return stack.pop();
    }

    @Override
    public String toString() {
        String repres="Stack:";
        for(T elem:stack) {
            repres += elem.toString() + "\n";
        }
        return repres;
    }

}
