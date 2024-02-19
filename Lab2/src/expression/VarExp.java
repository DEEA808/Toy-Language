package expression;

import model.MyIHeap;
import model.MyIMap;
import type.Type;
import value.Value;

public class VarExp implements Exp{
    public String id;
    public VarExp(String i){
        id=i;
    }

    @Override
    public String toString() {
        return "(id=" +  id +
                ')';
    }

    @Override
    public Value eval(MyIMap<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        if(tbl.getByKey(id)!=null)
            return tbl.getByKey(id);
          else  throw new MyException("invalid expr");

    }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        return typeEnvironment.getByKey(this.id);

    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }
}
