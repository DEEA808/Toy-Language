package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileTable<T,U> implements IFileTable<T,U> {
    public Map<T, U> fileMap;

    public FileTable() {
        fileMap = new HashMap<>();
    }
    @Override
    public void add(T key, U value)  {

            fileMap.put(key, value);
      //  else throw new MyException("the key does not exist");
    }

    @Override
    public HashMap<T, U> display() {
        return (HashMap<T, U>) fileMap;
    }

    @Override
    public U getByKey(T key) {
        for (T i : fileMap.keySet()) {
            if (i == key) {
                return fileMap.get(key);
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return fileMap.isEmpty();
    }

    @Override
    public void update(T key, U value) {
        for (T k : fileMap.keySet())
            if (k == key)
                fileMap.replace(key, value);
    }

    @Override
    public boolean IsVarDefined(T key) {
        for (T k : fileMap.keySet())
            if (k == key)
                return true;
        return false;
    }

    @Override
    public void remove(T key) {
        this.fileMap.remove(key);
    }

    @Override
    public String toString() {
        String repres="FileTable:[";
        Collection<T> keys=fileMap.keySet();
        repres+="]";
        return repres;
    }
}
