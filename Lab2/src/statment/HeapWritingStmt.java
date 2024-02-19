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

public class HeapWritingStmt implements IStmt{
    String id;
    Exp exp;

    @Override
    public String toString() {
        return "new("+this.id+","+this.exp.toString()+");\n";
    }

    public HeapWritingStmt(String id, Exp exp) {
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
                Value val=map.getByKey(id);
                int adr=((RefValue)val).getAdress();
                if(heap.IsVarDefined(adr)){
                    Value value = exp.eval(map, state.getHeap());
                    Type locationType = ((RefType) typeId).getInner();
                    if(value.getType().equals(locationType)){
                           heap.update(adr,value);
                    }else throw new MyException("the value type of the exp is not eq to locationType");
                }else throw new MyException("the adress is not defined");

            } else throw new MyException("declared type of variable id and type of the assigned expression do not match");

        } else throw new MyException("the use of the variable id was not declared before");
        return null;
    }

    @Override
    public MyIMap<String, Type> typeCheck(MyIMap<String, Type> typeEnvironment) throws MyException {
            Type type = typeEnvironment.getByKey(this.id);
            Type typeExpr=this.exp.typeCheck(typeEnvironment);
            if(type.equals(new RefType(typeExpr))){
                return typeEnvironment;
            }
            else
                throw new MyException("expr can not be evaluated to"+typeExpr);


    }

    @Override
    public IStmt deepCopy() {
        return new HeapWritingStmt(id,exp.deepCopy());
    }
}
