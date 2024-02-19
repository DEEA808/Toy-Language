package statment;

import expression.MyException;
import model.*;
import program.PrgState;
import type.Type;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;

public class ForkStmt implements IStmt {
    public IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIMap<String, Value> map = state.getSymTable();
        MyIList<Value> out = state.getOut();
        IFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        MyIMap<String, Value> newMap = new MyMap<>();
        newMap = map;
        MyIStack<IStmt> stk = new MyStack<>();
        int newId = state.newId();
        return new PrgState(stk, newMap, out, fileTable, heap, stmt, newId);
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        this.stmt.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }
}
