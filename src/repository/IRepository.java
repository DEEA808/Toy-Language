package repository;

import expression.MyException;
import program.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    // PrgState getCrtState();
     void addSate(PrgState state);
     void removeState(PrgState state) throws MyException;
     void logPrgStateExec(PrgState state) throws MyException, IOException;
    public void setStates(List<PrgState> states);
    public List<PrgState> getStates();
    public int getCurrIndex();

}
