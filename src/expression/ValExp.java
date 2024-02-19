package expression;

import model.MyIHeap;
import model.MyIMap;
import type.BoolType;
import type.IntType;
import type.StringType;
import type.Type;
import value.BooleanValue;
import value.IntValue;
import value.StringValue;
import value.Value;

public class ValExp implements Exp {
    public Value e;

    public ValExp(Value v) {
        e = v;
    }

    @Override
    public String toString() {
        return "(e=" + e +
                ')';
    }

    @Override
    public Value eval(MyIMap<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        if (e.getType().equals(new IntType())) {
            IntValue v1=(IntValue)e;
            int n1=v1.getVal();
            return new IntValue(n1);
        } else if (e.getType().equals(new BoolType())) {
            BooleanValue v2 = (BooleanValue) e;
            boolean n2 = v2.getVal();
            return new BooleanValue(n2);
        }else if(e.getType().equals(new StringType())){
            StringValue v3=(StringValue) e;
            String n3=v3.getVal();
            return new StringValue(n3);
        } else throw new MyException("The value e is not an integer or a bool or string");

    }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        return this.e.getType();
    }

    @Override
    public Exp deepCopy() {
        return new ValExp(e.deepCopy());
    }
}
