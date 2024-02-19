package statment;

import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import expression.Exp;
import type.BoolType;
import type.Type;
import value.BooleanValue;
import value.Value;

public class IfStmt implements IStmt {
    public Exp exp;
    public IStmt thenS;
    public IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt s) {
        exp = e;
        thenS = t;
        elseS = s;
    }

    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ")THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIMap<String, Value> map = state.getSymTable();
        Value cond = exp.eval(map,state.getHeap() );
        BooleanValue c;
        BooleanValue b = new BooleanValue(true);
        BooleanValue a = new BooleanValue(false);
        IStmt v;
        //!(cond instanceof BoolType)
        if (cond.equals(new BoolType())) {
            throw new MyException("cond expr is not a boolean");
        } else {
            BooleanValue condition=(BooleanValue) cond;
            if (b.getVal()==condition.getVal())
                 stk.push(thenS);
            else {
                if (a.getVal()==condition.getVal()) {
                    stk.push(elseS);
                }
            }
        }
        return null;


    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type typeExpression = this.exp.typeCheck(typeEnvironment);
            if (!typeExpression.equals(new BoolType())) {
                throw new MyException("The condition of IF has not the type bool.");
            }
            this.thenS.typeCheck(typeEnvironment.deepCopy());
            this.elseS.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenS.deepCopy(),elseS.deepCopy());
    }
}
