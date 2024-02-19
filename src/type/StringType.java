package type;

import value.StringValue;
import value.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof StringType;

    }
    public String toString(){
        return "String";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }
}
