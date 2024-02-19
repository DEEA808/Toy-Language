package statment;

import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.BoolType;
import type.IntType;
import type.Type;
import value.BooleanValue;
import value.IntValue;
import value.Value;

import java.util.Objects;

public class VarDeclStmt implements IStmt {
    public String name;
    public Type type;

    public VarDeclStmt(String n, Type t) {
        name = n;
        type = t;
    }

    @Override
    public String toString() {
        return "(" + name + " " + type.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIMap<String, Value> map = state.getSymTable();
        //BooleanValue defaultVal=new BooleanValue(false);
        //IntValue defval=new IntValue(0);
        if (map.IsVarDefined(name))
            throw new MyException("variable is already declared");
        else {
            map.add(name, type.defaultValue());
        }
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        typeEnvironment.add(this.name, this.type);
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, type.deepCopy());
    }

}
