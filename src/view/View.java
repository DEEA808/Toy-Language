//package view;
//
//import controller.Controller;
//import expression.*;
//import model.*;
//import program.PrgState;
//import repository.IRepository;
//import repository.Repository;
//import statment.*;
//import type.BoolType;
//import type.IntType;
//import type.StringType;
//import value.BooleanValue;
//import value.IntValue;
//import value.StringValue;
//import value.Value;
//
//import javax.management.ValueExp;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class View {
//
//    public void start(){
//        Scanner s=new Scanner(System.in);
//        System.out.println("Input the program nr");
//      //  System.out.println("Input file");
//        int op=s.nextInt();
//        if(op==1){
//            IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
//                    new CompStmt(new AssignStmt("v",new ValExp(new IntValue(2))) ,new PrintStmt(new LogicExp(new ValExp(new BooleanValue(true)),new ValExp(new BooleanValue(false)),1))));
//            MyIStack<IStmt> stk=new MyStack<IStmt>();
//            MyIMap<String, Value> tbl=new MyMap<String, Value>();
//            MyIList<Value> list=new MyList<Value>();
//            //stk.push(ex1);
//            IFileTable<StringValue, BufferedReader> fileTable=new FileTable<>();
//            PrgState state=new PrgState(stk,tbl,list,fileTable,ex1);
//            IRepository repo=new Repository("log4.txt");
//                repo.addSate(state);
//            Controller contr=new Controller(repo);
//            //contr.setDisplayFlag(false);
//            try {
//                contr.allStep();
//            } catch (MyException e) {
//                System.out.println(e.getMessage());
//            }
//        }else if(op==2){
//            IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
//                    new CompStmt(new VarDeclStmt("b",new IntType()),
//                            new CompStmt(new AssignStmt("a", new ArithExp(new ValExp(new IntValue(2)),new
//                                                                ArithExp(new ValExp(new IntValue(3)), new ValExp(new IntValue(5)),'*'),'+')),
//                                    new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValExp(new
//                                            IntValue(1)),'+')), new PrintStmt(new VarExp("b"))))));
//            MyIStack<IStmt> stk=new MyStack<IStmt>();
//            MyIMap<String, Value> tbl=new MyMap<String, Value>();
//            MyIList<Value> list=new MyList<Value>();
//            //stk.push(ex2);
//            IFileTable<StringValue, BufferedReader> fileTable=new FileTable<>();
//            PrgState state=new PrgState(stk,tbl,list,fileTable,ex2);
//            IRepository repo=new Repository("log4.txt");
//                repo.addSate(state);
//
//            Controller contr=new Controller(repo);
//            //contr.setDisplayFlag(false);
//            try {
//                contr.allStep();
//            } catch (MyException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }else if(op==3){
//           // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v) is represented as
////            IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
////                    new CompStmt(new VarDeclStmt("v", new IntType()),
////                            new CompStmt(new AssignStmt("a", new ValExp(new BooleanValue(true))),
////                                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValExp(new
////                                            IntValue(2))), new AssignStmt("v", new ValExp(new IntValue(3)))), new PrintStmt(new
////                                            VarExp("v"))))));
////            MyIStack<IStmt> stk=new MyStack<IStmt>();
////            MyIMap<String, Value> tbl=new MyMap<String, Value>();
////            MyIList<Value> list=new MyList<Value>();
////            IFileTable<String, BufferedReader> fileTable=new FileTable<>();
////            //stk.push(ex3)
////            PrgState state=new PrgState(stk,tbl,list,fileTable,ex3);
////            IRepository repo=new Repository("");
////            try {
////                repo.addSate(state);
////            } catch (MyException e) {
////                throw new RuntimeException(e);
////            }
////            Controller contr=new Controller(repo);
////            //contr.setDisplayFlag(false);
////            try {
////                contr.allStep();
////            } catch (MyException e) {
////                System.out.println(e.getMessage());
////            }catch(IOException e){
////                System.out.println("error");
////            }
//            IStmt stringDeclaration = new VarDeclStmt("varf", new StringType());
//            IStmt assignment = new AssignStmt("varf", new ValExp(new StringValue("./input/test.in")));
//            IStmt open = new OpenRFile(new VarExp("varf"));
//            IStmt intDeclaration = new VarDeclStmt("varc", new IntType());
//            IStmt readFile = new readFile(new VarExp("varf"), "varc");
//            IStmt print = new PrintStmt(new VarExp("varc"));
//            IStmt close = new CloseRFile(new VarExp("varf"));
//            IStmt ex4 = new CompStmt(stringDeclaration, new CompStmt(assignment, new CompStmt(open,
//                    new CompStmt(intDeclaration, new CompStmt(readFile, new CompStmt(print,
//                            new CompStmt(readFile, new CompStmt(print, close))))))));
//            MyIStack<IStmt> exeStack4 = new MyStack<>();
//            MyIMap<String, Value> symTable4 = new MyMap<>();
//            MyIList<Value> out4 = new MyList<>();
//            IFileTable<StringValue, BufferedReader>fileTable4 = new FileTable<>();
//            PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4, ex4);
//            IRepository repo = new Repository( "log5.txt");
//            repo.addSate(prg4);
//            Controller ctrl = new Controller(repo);
//            try {
//                ctrl.allStep();
//            } catch (MyException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }
//
//    }
//    public static void main(String[] args) throws MyException {
//        View v=new View();
//        v.start();
//    }
//}
