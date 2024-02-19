package value;

import type.BoolType;
import type.Type;

public class BooleanValue implements Value{
    public boolean boolValue;
    public BooleanValue(boolean bool){
        boolValue=bool;
    }
    public String toString(){
        return String.valueOf(boolValue);
    }
    public boolean getVal(){return boolValue;}
    boolean getBool(){
        return boolValue;
    }
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object another){
        return another instanceof BooleanValue &&((BooleanValue)another).getVal()==boolValue;

    }
    @Override
    public Value deepCopy() {
        return new BooleanValue(boolValue);
    }
}
