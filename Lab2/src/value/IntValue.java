package value;

import type.BoolType;
import type.IntType;
import type.Type;

public class IntValue implements Value {
    public int val;
    public IntValue(int value){
        val=value;
    }
    public int getVal(){return val;}
    public String toString(){
        return String.valueOf(val);

    }
    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object another){
        return another instanceof IntValue && ((IntValue)another).getVal()==val;

    }
    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

}
