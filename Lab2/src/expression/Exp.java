package expression;
import model.MyIHeap;
import model.MyIMap;
import value.Value;
import type.Type;
public interface Exp {
    Value eval(MyIMap<String,Value> tbl, MyIHeap<Integer, Value> heap)throws MyException;

    Type typeCheck(MyIMap<String,Type> typeEnvironment) throws MyException;

    Exp deepCopy();
}
