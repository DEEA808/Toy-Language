package statment;

import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.Type;

public class CompStmt implements IStmt{
    public IStmt first;
    public IStmt second;
    public CompStmt(IStmt f,IStmt s)
    {
        first=f;
        second=s;
    }
    @Override
    public String toString(){
        return "("+first.toString()+";"+second.toString()+")";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        return this.second.typeCheck(this.first.typeCheck(typeEnvironment));
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(),second.deepCopy());
    }

}
