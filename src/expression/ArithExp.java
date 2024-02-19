package expression;

import model.MyIHeap;
import model.MyIMap;
import type.IntType;
import type.Type;
import value.IntValue;
import value.Value;

public class ArithExp implements Exp{
    public Exp e1;
    public Exp e2;
    public char op;
    public ArithExp(Exp exp1,Exp exp2,char o){
        e1=exp1;
        e2=exp2;
        op=o;
    }

    @Override
    public String toString() {
        return "("+e1.toString()+op+e2.toString()+")";

    }

    @Override
    public Value eval(MyIMap<String,Value> tbl, MyIHeap<Integer, Value> heap)throws MyException{
         Value v1,v2;
         v1=e1.eval(tbl, heap);
         if(v1.getType().equals(new IntType())){
             v2=e2.eval(tbl, heap);
             if(v2.getType().equals((new IntType()))){
                 IntValue i1=(IntValue)v1;
                 IntValue i2=(IntValue) v2;
                 int n1,n2;
                 n1=i1.getVal();
                 n2=i2.getVal();
                 if(op=='+')return new IntValue(n1+n2);
                 if(op=='-') return new IntValue(n1-n2);
                 if(op=='*') return new IntValue(n1*n2);
                 if(op=='/')
                     if(n2==0) throw new MyException("division by zero");
                     else  return new IntValue(n1/n2);

                     }else throw new MyException("second operand is not an integer");

             }else throw new MyException("first operand is not an integer");

         throw new MyException("invalid expr");
         }

    @Override
    public Type typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type type1 = this.e1.typeCheck(typeEnvironment);
        Type type2 = this.e2.typeCheck(typeEnvironment);

        if (!type1.equals(new IntType())) {
            throw new MyException("First operand is not an integer");
        }
        if (!type2.equals(new IntType())) {
            throw new MyException("Second operand is not an integer");
        }

        return new IntType();

    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(e1.deepCopy(),e2.deepCopy(),op);
    }
}


