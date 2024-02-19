package statment;

import expression.Exp;
import expression.MyException;
import model.MyIHeap;
import model.MyIMap;
import program.PrgState;
import type.RefType;
import type.Type;
import value.RefValue;
import value.Value;

public class HeapAllocationStmt implements IStmt{
    String id;
    Exp exp;

    public HeapAllocationStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIMap<String, Value> map = state.getSymTable();
        MyIHeap<Integer,Value> heap=state.getHeap();
        if (map.IsVarDefined(id)) {
            Type typeId = (map.getByKey(id).getType());
            if (typeId instanceof RefType) {
                Type locationType = ((RefType) typeId).getInner();
                Value val = exp.eval(map, state.getHeap());
                if(val.getType().equals(locationType)){
                    int adress_new=heap.add(val);
                    map.update(id,new RefValue(adress_new,locationType));}

            }
            else {

                throw new MyException("declared type of variable id and type of the assigned expression do not match");
            }
        } else throw new MyException("the use of the variable id was not declared before");
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type typeVariable = typeEnvironment.getByKey(this.id);
            Type typeExpression = this.exp.typeCheck(typeEnvironment);
            if (!typeVariable.equals(new RefType(typeExpression))) {
                throw new MyException("Declared type of variable and type of the assigned expression do not match in HeapAllocationStmt.");
            }
        return typeEnvironment;

    }

    @Override
    public String toString() {
        return "new("+this.id+","+this.exp.toString()+");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocationStmt(id,exp.deepCopy());
    }
}
