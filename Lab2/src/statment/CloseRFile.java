package statment;

import expression.Exp;
import expression.MyException;
import model.IFileTable;
import model.MyIMap;
import program.PrgState;
import type.StringType;
import type.Type;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    Exp exp;

    public CloseRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return exp.toString() ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        MyIMap<String,Value> map=state.getSymTable();
        Value val=exp.eval(map, state.getHeap());
        if(!val.getType().equals(new StringType()))
            throw new MyException("not a string");
        if(!fileTable.IsVarDefined((StringValue) val))
            throw  new MyException("is not defined");
        BufferedReader fileBuf=fileTable.getByKey((StringValue) val);
        try {
            fileBuf.close();
        }catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        fileTable.remove((StringValue) val);
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new MyException("Expression in CloseRFileStatement is not of type string.");
            }
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }
}
