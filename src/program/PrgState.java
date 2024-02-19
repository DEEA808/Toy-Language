package program;

import expression.MyException;
import model.*;
import statment.IStmt;
import value.StringValue;
import value.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIMap<String, Value> symTable;
    private MyIList<Value> out;
    private MyIHeap<Integer, Value> heap;
    IStmt originalPrg;

    private static int id;

    private IFileTable<StringValue, BufferedReader> fileTable;

    public PrgState(MyIStack<IStmt> exeStack, MyIMap<String, Value> symTable, MyIList<Value> out, IFileTable<StringValue, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, IStmt orig, int id) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        PrgState.id = id;
        this.originalPrg = orig.deepCopy();
        this.exeStack.push(originalPrg);


    }

    public static synchronized int newId() {
        int neww=id+1;
        return neww;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer, Value> heap) {
        this.heap = heap;
    }

    public IStmt getOriginalPrg() {
        return originalPrg;
    }

    public void setOriginalPrg(IStmt originalPrg) {
        this.originalPrg = originalPrg;
    }

    public IFileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIMap<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIMap<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" + this.exeStack.toString() + "\n" + this.symTable.toString() + "\n" + this.out.toString() + "\n" + this.fileTable.toString() + "\n" + this.heap.toString() + "\n";
    }

    public void Update(MyIMap<String, Value> map, String id, Value v) {
        map.update(id, v);
    }

    public Boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if (this.exeStack.isEmpty()) throw new MyException("prg stack is empty");
        IStmt crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }
}
