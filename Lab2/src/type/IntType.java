package type;

import value.IntValue;
import value.StringValue;
import value.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }
    public String toString(){
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
    @Override
    public Type deepCopy() {
        return new IntType();
    }

}
