package statment;

import expression.Exp;
import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.BoolType;
import type.Type;
import value.BooleanValue;
import value.Value;

public class WhileStmt implements IStmt{
    Exp exp;
    IStmt stmt;

    @Override
    public String toString() {
        return "while(" +
                exp.toString() +
                "){" + stmt.toString() +
                '}';
    }

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIMap<String, Value> map = state.getSymTable();

        Value cond = exp.eval(map,state.getHeap());
        if(cond.getType().equals(new BoolType())){
            if(((BooleanValue) cond).getVal()) {
                stk.push(this);
                stk.push(stmt);
            }
        }
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type expressionType = this.exp.typeCheck(typeEnvironment);
            if (!expressionType.equals(new BoolType())) {
                throw new MyException("Expression " + this.exp + " is not a boolean");
            }
            this.stmt.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;

    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(exp.deepCopy(), stmt.deepCopy()) ;
    }
}
