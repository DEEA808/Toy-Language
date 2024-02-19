package expression;

import model.MyIHeap;
import model.MyIMap;
import type.RefType;
import type.Type;
import value.RefValue;
import value.Value;

public class HeapReadExp implements Exp {
    Exp exp;

    public HeapReadExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "HeapReadExp(" + exp.toString() +
                ')';
    }

    @Override
    public Value eval(MyIMap<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value val = exp.eval(tbl, heap);
        if (val instanceof RefValue) {
            int adress = ((RefValue) val).getAdress();
            if (heap.IsVarDefined(adress)) {
                Value valueHeap = heap.getByKey(adress);
                return valueHeap;
            } else throw new MyException("The address was not defined");
        } else
            throw new MyException("the value is not a RefValue");
    }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type type = this.exp.typeCheck(typeEnvironment);
        if(type instanceof RefType){
            RefType ref=(RefType) type;
            return ref.getInner();
        }else
            throw new MyException("this instance is not a ref type");

    }

    @Override
    public Exp deepCopy() {
        return new HeapReadExp(exp.deepCopy());
    }
}
