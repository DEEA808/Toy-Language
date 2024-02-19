package repository;

import expression.MyException;
import program.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> states;
    public int CurrIndex;
    public String logFilePath;


    public Repository(String logFile) {
        this.states = new ArrayList<>();
        this.CurrIndex = -1;
        this.logFilePath = logFile;

    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException, IOException {

        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(state.toString());
        logFile.close();
    }

    public List<PrgState> getStates() {
        return states;
    }

    public void setStates(List<PrgState> states) {
        this.states = states;
    }

    public int getCurrIndex() {
        return CurrIndex;
    }

    public void setCurrIndex(int currIndex) {
        CurrIndex = currIndex;
    }


//    @Override
//    public PrgState getCrtState() {
//        return states.getFirst();
//    }

    @Override
    public void addSate(PrgState state) {
        this.CurrIndex = this.CurrIndex + 1;
        states.add(state);
    }

    @Override
    public void removeState(PrgState state) throws MyException {
        this.states.remove(CurrIndex);
    }
}
