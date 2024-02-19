package statment;

import expression.MyException;
import model.MyIMap;
import program.PrgState;
import type.Type;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state)throws MyException;
    MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException;
    public IStmt deepCopy();
}
