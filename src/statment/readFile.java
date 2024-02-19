package statment;

import expression.Exp;
import expression.MyException;
import model.IFileTable;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.IntType;
import type.StringType;
import type.Type;
import value.IntValue;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    Exp exp;
    String id;

    public readFile(Exp exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "readFile(" +
                 exp.toString() +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        MyIMap<String,Value> map=state.getSymTable();
        IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        if(!map.IsVarDefined(id))
            throw new MyException("The key is not defined");
        Value filePathVal=exp.eval(map, state.getHeap() );
        if(!filePathVal.getType().equals(new StringType()))
            throw  new MyException("Is not a string");
        try{
            BufferedReader buf=fileTable.getByKey((StringValue) filePathVal);
            String line=buf.readLine();
            if(line==null)
                map.update(id,new IntValue(0));
            else
                map.update(id,new IntValue(Integer.parseInt(line)));

    }catch (IOException e){
        throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new StringType())) {
                throw new MyException("Expression in ReadFileStatement is not of type string.");
            }
            Type idType = typeEnvironment.getByKey(this.id);
            if (!idType.equals(new IntType())) {
                throw new MyException("Variable in ReadFileStatement is not of type int.");
            }

        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new readFile(exp.deepCopy(),id);
    }
}
