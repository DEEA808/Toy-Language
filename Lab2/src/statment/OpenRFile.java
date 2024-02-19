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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt{
    Exp exp;

    public OpenRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "OpenRFile(" +
                exp.toString() +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        MyIMap<String,Value> m= state.getSymTable();
        Value val=exp.eval(m,state.getHeap() );
        if(!val.getType().equals(new StringType()))
            throw new MyException("not a string");
        if(fileTable.IsVarDefined((StringValue) val)) {
            throw new MyException("is already defined");
        }
        try{
            BufferedReader fileBuffer = new BufferedReader(new FileReader(((StringValue)val).getVal()));
            fileTable.add((StringValue)val, fileBuffer);
        }catch (FileNotFoundException e){
            throw new MyException("file not found");
        }

        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new MyException("Expression is not of type string in OpenRFileStatement.");
            }
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());}
}
