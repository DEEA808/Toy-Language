import controller.Controller;
import expression.*;
import model.*;
import program.PrgState;
import repository.IRepository;
import repository.Repository;
import statment.*;
import type.BoolType;
import type.IntType;
import type.RefType;
import type.StringType;
import value.*;
import view.textInterface.Command;
import view.textInterface.ExitCommand;
import view.textInterface.RunExampleCommand;
import view.textInterface.TextInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

import type.Type;

public class Main {
    public static void main(String[] args) throws MyException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter filename: ");
        String filename = scanner.nextLine();
        filename = "log/" + filename;
        TextInterface meniu = new TextInterface();
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(2))), new PrintStmt(new LogicExp(new ValExp(new IntValue(10)), new ValExp(new BooleanValue(false)), 1))));
        MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIMap<String, Value> tbl1 = new MyMap<String, Value>();
        MyIList<Value> list1 = new MyList<Value>();
        MyIHeap<Integer, Value> heap1 = new MyHeap<>();
        IFileTable<StringValue, BufferedReader> fileTable1 = new FileTable<>();
        MyIMap<String, Type> typeEnvironment1 = new MyMap<>();
        try {
            ex1.typeCheck(typeEnvironment1);
            PrgState state1 = new PrgState(stk1, tbl1, list1, fileTable1, heap1, ex1, 1);
            IRepository repo1 = new Repository(filename);
            repo1.addSate(state1);
            Controller contr1 = new Controller(repo1);
            meniu.addCommand(new RunExampleCommand("1", ex1.toString(), contr1));
        } catch (MyException e) {
            System.out.println("ex1 type check failed");

        }

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValExp(new IntValue(2)), new
                                ArithExp(new ValExp(new IntValue(3)), new ValExp(new IntValue(5)), '*'), '+')),
                                new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValExp(new
                                        IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        MyIMap<String, Value> tbl2 = new MyMap<String, Value>();
        MyIList<Value> list2 = new MyList<Value>();
        IFileTable<StringValue, BufferedReader> fileTable2 = new FileTable<>();
        MyIHeap<Integer, Value> heap2 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment2 = new MyMap<>();
        try {
            ex2.typeCheck(typeEnvironment2);
            PrgState state2 = new PrgState(stk2, tbl2, list2, fileTable2, heap2, ex2, 1);
            IRepository repo2 = new Repository(filename);
            repo2.addSate(state2);
            Controller contr2 = new Controller(repo2);
            meniu.addCommand(new RunExampleCommand("2", ex2.toString(), contr2));
        } catch (MyException e) {
            System.out.println("ex2 type check failed");
            ;
        }

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValExp(new BooleanValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValExp(new
                                        IntValue(2))), new AssignStmt("v", new ValExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        MyIStack<IStmt> stk3 = new MyStack<IStmt>();
        MyIMap<String, Value> tbl3 = new MyMap<String, Value>();
        MyIList<Value> list3 = new MyList<Value>();
        IFileTable<StringValue, BufferedReader> fileTable3 = new FileTable<>();
        MyIHeap<Integer, Value> heap3 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment3 = new MyMap<>();
        try {
            ex3.typeCheck(typeEnvironment3);
            PrgState state3 = new PrgState(stk3, tbl3, list3, fileTable3, heap3, ex3, 1);
            IRepository repo3 = new Repository(filename);
            repo3.addSate(state3);
            Controller contr3 = new Controller(repo3);
            meniu.addCommand(new RunExampleCommand("3", ex3.toString(), contr3));
        } catch (MyException e) {
            System.out.println("ex3 type check failed");
            ;
        }


        IStmt stringDeclaration = new VarDeclStmt("varf", new StringType());
        IStmt assignment = new AssignStmt("varf", new ValExp(new StringValue("./input/test.in")));
        IStmt open = new OpenRFile(new VarExp("varf"));
        IStmt intDeclaration = new VarDeclStmt("varc", new IntType());
        IStmt readFile = new readFile(new VarExp("varf"), "varc");
        IStmt print = new PrintStmt(new VarExp("varc"));
        IStmt close = new CloseRFile(new VarExp("varf"));
        IStmt ex4 = new CompStmt(stringDeclaration, new CompStmt(assignment, new CompStmt(open,
                new CompStmt(intDeclaration, new CompStmt(readFile, new CompStmt(print,
                        new CompStmt(readFile, new CompStmt(print, close))))))));
        MyIStack<IStmt> exeStack4 = new MyStack<>();
        MyIMap<String, Value> symTable4 = new MyMap<>();
        MyIList<Value> out4 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable4 = new FileTable<>();
        MyIHeap<Integer, Value> heap4 = new MyHeap<>();
        PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4, heap4, ex4, 1);
        IRepository repo4 = new Repository("log5.txt");
        repo4.addSate(prg4);
        Controller cntr4 = new Controller(repo4);


        IStmt refDeclaration = new VarDeclStmt("v", new RefType(new IntType()));
        IStmt alloc = new HeapAllocationStmt("v", new ValExp(new IntValue(20)));
        IStmt alloca = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        IStmt vina = new HeapAllocationStmt("a", new VarExp("v"));
        IStmt alloc30 = new HeapAllocationStmt("v", new ValExp(new IntValue(30)));
        IStmt print1 = new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))));
        IStmt ex5 = new CompStmt(refDeclaration, new CompStmt(alloc, new CompStmt(alloca, new CompStmt(vina, new CompStmt(alloc30, print1)))));

        MyIStack<IStmt> exeStack5 = new MyStack<>();
        MyIMap<String, Value> symTable5 = new MyMap<>();
        MyIList<Value> out5 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable5 = new FileTable<>();
        MyIHeap<Integer, Value> heap5 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment5 = new MyMap<>();
        try {
            ex5.typeCheck(typeEnvironment5);
            PrgState prg5 = new PrgState(exeStack5, symTable5, out5, fileTable5, heap5, ex5, 1);
            IRepository repo5 = new Repository(filename);
            repo5.addSate(prg5);
            Controller cntr5 = new Controller(repo5);
            meniu.addCommand(new RunExampleCommand("5", ex5.toString(), cntr5));
        } catch (MyException e) {
            System.out.println("ex5 type check failed");
            ;
        }

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStmt("v", new ValExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStmt("v", new ValExp(new IntValue(30))),
                                        new CompStmt(new HeapAllocationStmt("a", new VarExp("v")),
                                                new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        MyIStack<IStmt> exeStack6 = new MyStack<>();
        MyIMap<String, Value> symTable6 = new MyMap<>();
        MyIList<Value> out6 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable6 = new FileTable<>();
        MyIHeap<Integer, Value> heap6 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment6 = new MyMap<>();
        try {
            ex6.typeCheck(typeEnvironment6);
            PrgState prg6 = new PrgState(exeStack6, symTable6, out6, fileTable6, heap6, ex6, 1);
            IRepository repo6 = new Repository(filename);
            repo6.addSate(prg6);
            Controller cntr6 = new Controller(repo6);
            meniu.addCommand(new RunExampleCommand("6", ex6.toString(), cntr6));
        } catch (MyException e) {
            System.out.println("ex6 type check failed");
            ;
        }

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValExp(new IntValue(1)), '-')))),
                                new PrintStmt(new VarExp("v")))));

        MyIStack<IStmt> exeStack7 = new MyStack<>();
        MyIMap<String, Value> symTable7 = new MyMap<>();
        MyIList<Value> out7 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable7 = new FileTable<>();
        MyIHeap<Integer, Value> heap7 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment7 = new MyMap<>();
        try {
            ex7.typeCheck(typeEnvironment7);
            PrgState prg7 = new PrgState(exeStack7, symTable7, out7, fileTable7, heap7, ex7, 1);
            IRepository repo7 = new Repository(filename);
            repo7.addSate(prg7);
            Controller cntr7 = new Controller(repo7);
            meniu.addCommand(new RunExampleCommand("7", ex7.toString(), cntr7));
        } catch (MyException e) {
            System.out.println("ex7 type check failed");
            ;
        }


        IStmt varDecl = new VarDeclStmt("v", new IntType());
        IStmt refDecl = new VarDeclStmt("a", new RefType(new IntType()));
        IStmt assignDecl = new AssignStmt("v", new ValExp(new IntValue(10)));
        IStmt allocDecl = new HeapAllocationStmt("a", new ValExp(new IntValue(22)));
        IStmt wDeclFork = new HeapWritingStmt("a", new ValExp(new IntValue(30)));
        IStmt assignDeclFork = new AssignStmt("v", new ValExp(new IntValue(32)));
        IStmt printFork = new PrintStmt(new VarExp("v"));
        IStmt readFork = new PrintStmt(new HeapReadExp(new VarExp("a")));
        IStmt forkStmt = new ForkStmt(new CompStmt(wDeclFork, new CompStmt(assignDeclFork, new CompStmt(printFork, readFork))));
        IStmt printStmt = new PrintStmt(new VarExp("v"));
        IStmt readStmt = new PrintStmt(new HeapReadExp(new VarExp("a")));
        IStmt ex8 = new CompStmt(varDecl, new CompStmt(refDecl, new CompStmt(assignDecl, new CompStmt(allocDecl, new CompStmt(forkStmt, new CompStmt(printStmt, readStmt))))));

        MyIStack<IStmt> exeStack8 = new MyStack<>();
        MyIMap<String, Value> symTable8 = new MyMap<>();
        MyIList<Value> out8 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable8 = new FileTable<>();
        MyIHeap<Integer, Value> heap8 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment8 = new MyMap<>();
        try {
            ex8.typeCheck(typeEnvironment8);
            PrgState prg8 = new PrgState(exeStack8, symTable8, out8, fileTable8, heap8, ex8, 1);
            IRepository repo8 = new Repository(filename);
            repo8.addSate(prg8);
            Controller cntr8 = new Controller(repo8);
            meniu.addCommand(new RunExampleCommand("8", ex8.toString(), cntr8));
        } catch (MyException e) {
            System.out.println("ex8 type check failed");

        }

        IStmt varDecl1 = new VarDeclStmt("counter", new IntType());
        IStmt whileStmt = new WhileStmt(new RelationalExp(new VarExp("counter"), new ValExp(new IntValue(10)), 1), new CompStmt(new ForkStmt(new ForkStmt(new HeapAllocationStmt("a", new VarExp("counter")))), new AssignStmt("counter", new ArithExp(new VarExp("counter"), new ValExp(new IntValue(1)), '+'))));
        IStmt ex9 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(varDecl1, whileStmt));

        MyIStack<IStmt> exeStack9 = new MyStack<>();
        MyIMap<String, Value> symTable9 = new MyMap<>();
        MyIList<Value> out9 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable9 = new FileTable<>();
        MyIHeap<Integer, Value> heap9 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment9 = new MyMap<>();
        try{
            ex9.typeCheck(typeEnvironment9);
            PrgState prg9 = new PrgState(exeStack9, symTable9, out9, fileTable9, heap9, ex9, 1);
            IRepository repo9 = new Repository(filename);
            repo9.addSate(prg9);
            Controller cntr9 = new Controller(repo9);
            meniu.addCommand(new RunExampleCommand("9", ex9.toString(), cntr9));
        } catch (MyException e) {
            System.out.println("ex9 type check failed");
            ;
        }

        //For
        IStmt ex10 =new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValExp(new IntValue(20))),
                new CompStmt(new ForStmt("v", new ValExp(new IntValue(0)), new ValExp(new IntValue(3)), new ArithExp( new VarExp("v"), new ValExp(new IntValue(1)),'+'),
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp(new VarExp("v"), new ValExp(new IntValue(1)),'+'))))),
                        new PrintStmt(new ArithExp(new VarExp("v"), new ValExp(new IntValue(10)),'*')))));







        MyIStack<IStmt> exeStack10 = new MyStack<>();
        MyIMap<String, Value> symTable10 = new MyMap<>();
        MyIList<Value> out10 = new MyList<>();
        IFileTable<StringValue, BufferedReader> fileTable10 = new FileTable<>();
        MyIHeap<Integer, Value> heap10 = new MyHeap<>();
        MyIMap<String, Type> typeEnvironment10 = new MyMap<>();
        PrgState prg10 = new PrgState(exeStack10, symTable10, out10, fileTable10, heap10, ex10,1);
        IRepository repo10 = new Repository("log10.txt");
        repo10.addSate(prg10);
        Controller cntr10 = new Controller(repo10);
        RunExampleCommand r10= new RunExampleCommand("10", ex10.toString(), cntr10);

        meniu.addCommand(r10);
        meniu.addCommand(new ExitCommand("0", "EXIT!"));
        meniu.addCommand(new RunExampleCommand("4", ex4.toString(), cntr4));
        meniu.show();
    }
}