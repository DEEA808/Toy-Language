package type;

import expression.RelationalExp;
import value.IntValue;
import value.RefValue;
import value.Value;

public class RefType implements Type{
    Type inner;
    public RefType(Type inner){
        this.inner=inner;
    }
    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }
    public boolean equals(Object another){
        return another instanceof RefType && inner.equals(((RefType) another).getInner());
    }

    public String toString(){
        return "Ref("+inner.toString()+")";
    }
    public Type getInner() {
        return inner;
    }

    @Override
    public Type deepCopy() {
        return new RefType(this.inner);
    }
}
