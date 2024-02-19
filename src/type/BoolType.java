package type;

import value.BooleanValue;
import value.StringValue;
import value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;

    }
    public String toString(){
        return "boolean";
    }
    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }
    @Override
    public Type deepCopy() {
        return new BoolType();
    }
}
