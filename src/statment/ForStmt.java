package statment;

import expression.Exp;
import expression.MyException;
import expression.RelationalExp;
import expression.VarExp;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import type.IntType;
import type.Type;

public class ForStmt implements IStmt{
    public String var;
    public Exp exp1;
    public Exp exp2;
    public Exp exp3;

    public IStmt stmt;

    public ForStmt(String var, Exp exp1, Exp exp2, Exp exp3, IStmt stmt) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "for(" + var + "=" + exp1+";" + var+"<"+
                exp2+";" + var+"="+
                exp3+ ")" + stmt.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IStmt newStmt= new CompStmt(new AssignStmt(var, exp1),
                new WhileStmt(new RelationalExp( new VarExp(var), exp2,1),
                        new CompStmt(stmt, new AssignStmt(var, exp3))));
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(newStmt);
        //state.setExeStack(stk);
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type type1=this.exp1.typeCheck(typeEnvironment);
        Type type2=this.exp2.typeCheck(typeEnvironment);
        Type type3=this.exp3.typeCheck(typeEnvironment);

        if(type1.equals(new IntType()) && type2.equals(new IntType()) && type3.equals(new IntType()))
            return typeEnvironment;
        else
            throw new MyException("The for statment is not valid");
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(var, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), stmt.deepCopy());
    }
}
