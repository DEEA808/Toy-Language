package type;

import value.Value;

public interface Type {
    Value defaultValue();
    public Type deepCopy();
}
