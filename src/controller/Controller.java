package controller;

import expression.MyException;
import model.MyIStack;
import program.PrgState;
import repository.IRepository;
import statment.IStmt;
import value.RefValue;
import value.Value;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class Controller {
    public IRepository repo;
    boolean displayFlag;
    ExecutorService executor;

    public Controller(IRepository r) {
        this.repo = r;
        this.displayFlag = true;
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
                if(isDisplayFlag())
                    System.out.println(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        repo.setStates(prgList);
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getStates());
        while (prgList.size() > 0) {
            prgList.get(repo.getCurrIndex()).getHeap().setHeap(unsafeGarbageCollector(
                    getAddrFromSymTable(prgList.get(repo.getCurrIndex()).getSymTable().display().values()),
                    prgList.get(repo.getCurrIndex()).getHeap().display()));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getStates());
        }
        executor.shutdownNow();
        repo.setStates(prgList);
    }

    public boolean isDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }
    //public void displayCurrentProgrState(){

    //}
    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        List<Integer> heapAddr = getAddrFromHeap(heap.values());
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAdress();
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAdress();
                })
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }


}
