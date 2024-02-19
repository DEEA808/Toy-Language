package expression;

import model.MyIHeap;
import model.MyIMap;
import type.BoolType;
import type.IntType;
import type.Type;
import value.BooleanValue;
import value.IntValue;
import value.Value;

public class RelationalExp implements Exp{
    Exp exp1;
    Exp exp2;
    int op;

    public RelationalExp(Exp exp1, Exp exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public Value eval(MyIMap<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value n1 = exp1.eval(tbl,heap );
        if(n1.getType().equals(new IntType()))
        {
            Value n2 = exp2.eval(tbl, heap);
            if(n2.getType().equals(new IntType()))
            {
                int i1, i2;
                i1 = ((IntValue) n1).getVal();
                i2 = ((IntValue) n2).getVal();
                if(op == 1) return new BooleanValue(i1<i2);
                else if(op == 2) return new BooleanValue(i1<=i2);
                else if(op == 3) return new BooleanValue(i1==i2);
                else if(op == 4) return new BooleanValue(i1!=i2);
                else if(op == 5) return new BooleanValue(i1>i2);
                else if(op == 6) return new BooleanValue(i1>=i2);

                else throw new MyException("Invalid operator");
            }
            else throw new MyException("Second operand is not int!");
        }
        else throw new MyException("First operand is not int!");
    }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type type1 = this.exp1.typeCheck(typeEnvironment);
        Type type2 = this.exp2.typeCheck(typeEnvironment);

        if (!type1.equals(new IntType())) {
            throw new MyException("First operand is not an integer");
        }
        if (!type2.equals(new IntType())) {
            throw new MyException("Second operand is not an integer");
        }

        return new BoolType();

    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(exp1.deepCopy(), exp2.deepCopy(), op);
    }

    @Override
    public String toString() {
        String oper="";
        if(op==1)
            oper="<=";
        if(op==2)
            oper="==";
        if(op==3)
            oper="!=";
        if(op==4)
            oper=">";
        if(op==5)
            oper=">=";
        return "("+exp1.toString()+oper+exp2.toString()+")";
    }
}
