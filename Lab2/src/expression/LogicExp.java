package expression;

import model.MyIHeap;
import model.MyIMap;
import type.BoolType;
import type.Type;
import value.BooleanValue;
import value.Value;

public class LogicExp implements Exp{
    public Exp exp1;
    public Exp exp2;
    public int op;

    @Override
    public String toString() {
        String oper="";
        if(op==1)
            oper="&&";
        if(op==2)
            oper="||";
        return "("+exp1.toString()+oper+exp2.toString()+")";
    }

    public LogicExp(Exp e1, Exp e2, int o){
        exp1=e1;
        exp2=e2;
        op=o;
    }
    @Override
    public Value eval(MyIMap<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1,v2;
        v1=exp1.eval(tbl,heap );
        if(v1.getType().equals(new BoolType())){
            v2=exp2.eval(tbl,heap );
            if(v2.getType().equals(new BoolType())){
                BooleanValue b1=(BooleanValue)v1;
                BooleanValue b2=(BooleanValue) v2;
                boolean n1,n2;
                n1=b1.getVal();
                n2=b2.getVal();
                if(op==1){return new BooleanValue(n1&&n2);}
                if(op==2){return new BooleanValue(n1||n2);}

            }else throw new MyException("second operand is not boolean");
        }else throw new MyException("first operand is not a boolean");
        throw new MyException("ivalid expression");
    }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type type1 = this.exp1.typeCheck(typeEnvironment);
        Type type2 = this.exp2.typeCheck(typeEnvironment);

        if (!type1.equals(new BoolType())) {
            throw new MyException("First operand is not a bool");
        }
        if (!type2.equals(new BoolType())) {
            throw new MyException("Second operand is not a bool");
        }

        return new BoolType();

    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(exp1.deepCopy(),exp2.deepCopy(),op);
    }
}
