package value;

import type.BoolType;
import type.StringType;
import type.Type;

public class StringValue implements Value{
    String string;

    public StringValue(String s){this.string=s;}

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(string);
    }

    public String getVal(){return this.string;}
    public String toString(){
        return this.string;

    }
    @Override
    public boolean equals(Object another){
        return another instanceof StringValue &&((StringValue)another).getVal()==this.string;

    }
}
