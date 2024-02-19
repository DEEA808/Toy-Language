package value;

import type.RefType;
import type.Type;

public class RefValue implements Value{
    int adress;
    Type locationType;

    public RefValue(int adress, Type locationType) {
        this.adress = adress;
        this.locationType = locationType;
    }

    public int getAdress() {
        return adress;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(adress,locationType);
    }

    @Override
    public String toString() {
        return "(" +adress + " " + locationType +
                ')';
    }
    @Override
    public boolean equals(Object another){
        return another instanceof RefValue &&((RefValue)another).getType()==this.locationType && ((RefValue)another).getAdress()==this.adress;

    }

}
