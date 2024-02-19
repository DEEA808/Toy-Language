package statment;

import expression.MyException;
import model.MyIMap;
import model.MyIStack;
import program.PrgState;
import expression.Exp;
import value.Value;
import type.Type;
public class AssignStmt implements IStmt{
    public String id;
    public Exp exp;
    public AssignStmt(String i,Exp e){
        id=i;
        exp=e;
    }
    @Override
    public String toString(){
        return id+"="+exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        MyIMap<String,Value> map=state.getSymTable();
        if(map.IsVarDefined(id)){
            Value val=exp.eval(map, state.getHeap() );
            Type typeId=(map.getByKey(id).getType());
            if(val.getType().equals(typeId))
                state.Update(map,id,val);
            else {

                throw new MyException("declared type of variable id and type of the assigned expression do not match");
                       }
            }
        else throw new MyException("the use of the variable id was not declared before");
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
        Type typeVar = typeEnvironment.getByKey(this.id);
        Type typeExp = exp.typeCheck(typeEnvironment);
        if(typeVar.equals(typeExp))
            return typeEnvironment;
        else
            throw new MyException("AssignStmt: right hand side and left hand side have different types");

    }

    @Override
    public IStmt deepCopy() {
        return  new AssignStmt(id, exp.deepCopy());
    }
}
