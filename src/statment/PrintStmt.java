package statment;

import expression.MyException;
import model.MyIList;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import expression.Exp;
import type.Type;
import value.Value;
public class PrintStmt implements IStmt{
    public Exp exp;
    public PrintStmt(Exp expr){
        exp=expr;
    }
    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        MyIMap<String,Value> tbl=state.getSymTable();
        MyIList<Value> list=state.getOut();
        try {

            Value value = exp.eval(tbl, state.getHeap() );
            list.add(value);

        }catch(MyException e){
            throw new MyException("Inv expr");
        }
        return null;

    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        this.exp.typeCheck(typeEnvironment);
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
