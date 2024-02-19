package statment;

import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.Type;

public class NopStmt implements IStmt{

    public String toString(){
        return "nop";
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {

        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
