package fr.imt.haskell.interpreter;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.ConditionalExpression;
import fr.imt.haskell.interpreter.ast.builtin.Recursion;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Minus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Plus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Times;
import fr.imt.haskell.interpreter.ast.builtin.lists.Head;
import fr.imt.haskell.interpreter.ast.builtin.lists.Length;
import fr.imt.haskell.interpreter.ast.builtin.lists.Null;
import fr.imt.haskell.interpreter.ast.builtin.lists.Tail;
import fr.imt.haskell.interpreter.ast.builtin.logicals.Equal;
import fr.imt.haskell.interpreter.ast.builtin.logicals.GreaterThan;
import fr.imt.haskell.interpreter.ast.builtin.logicals.LessThanOrEqual;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.ArrayList;

import static fr.imt.haskell.interpreter.ast.constants.List.Cons;
import static fr.imt.haskell.interpreter.ast.constants.List.Nil;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

    final ArrayList<Expression> expressions = new ArrayList<>();

    // (((\w -> w) (\x -> (x x))) ((\y -> y) (\z -> z)))
    expressions.add(
        new Application(
            new Application(
                new Lambda(new Variable("w"), new Variable("w")),
                new Lambda(
                    new Variable("x"), new Application(new Variable("x"), new Variable("x")))),
            new Application(
                new Lambda(new Variable("y"), new Variable("y")),
                new Lambda(new Variable("z"), new Variable("z")))));

    expressions.add(
        new Application(
            new Lambda(new Variable("x"), new Times(new Variable("x"), new Number(2))),
            new Application(
                new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
                new Plus(new Number(5), new Number(2)))));

    final List list =
        Cons(new Number(1), Cons(new Number(2), Cons(new Number(4), Cons(new Number(5), Nil()))));
    final List listUnordered =
        Cons(new Number(2), Cons(new Number(1), Cons(new Number(5), Cons(new Number(4), Nil()))));

    expressions.add(new Tail(list));
    expressions.add(new Null(list));
    expressions.add(new Head(list));
    expressions.add(new Length(list));
    expressions.add(new Equal(new Head(list), new Number(1)));
    expressions.add(
        myMap(new Lambda(new Variable("x"), new Plus(new Variable("x"), new Number(1))), list));
    expressions.add(myInsert(new Number(3), list));
    expressions.add(myTake(new Number(2), list));
    // expressions.add(mySort(listUnordered));

//    reduceByName(infiniteList(), false);
//    reduceByValue(infiniteList(), false);
//    reduceByNeed(infiniteList(), false);
    // reduceByName(infiniteList(), true);
    // reduceByValue(infiniteList(), true);
    // reduceByNeed(infiniteList(), true);

    expressions.forEach(
        exp -> {
          reduceByName(exp, true);
          reduceByValue(exp, true);
          reduceByNeed(exp, true);
        });
  }

  private static void reduceByName(final Expression exp, boolean printBelowList) {
    System.out.println("\nExpression to reduce by name: " + exp + "\n");
    System.out.println(exp);

    final Printer printer = new Printer(printBelowList, exp);
    final Expression result = exp.reduce(printer);
    System.out.println("Result: " + result);
    System.out.println("Reduction steps: " + printer.getReductionSteps() + "\n");
  }

  private static void reduceByValue(final Expression exp, boolean printBelowList) {
    System.out.println("\nExpression to reduce by value: " + exp + "\n");
    System.out.println(exp);

    final Printer printer = new Printer(printBelowList, exp);
    final Expression result = exp.reduceByValue(printer);
    System.out.println("Result: " + result);
    System.out.println("Reduction steps: " + printer.getReductionSteps() + "\n");
  }

  private static void reduceByNeed(final Expression exp, boolean printBelowList) {
    System.out.println("\nExpression to reduce by need: " + exp + "\n");
    System.out.println(exp);

    final Printer printer = new Printer(printBelowList, exp);
    final Expression result = exp.reduceByNeed(printer);
    System.out.println("Result: " + result);
    System.out.println("Reduction steps: " + printer.getReductionSteps() + "\n");
  }

  private static Expression factorial(final int value) {
    return new Application(
        new Recursion(
            new Lambda(
                new Variable("fac"),
                new Lambda(
                    new Variable("n"),
                    new ConditionalExpression(
                        new Equal(new Variable("n"), new Number(0)),
                        new Number(1),
                        new Times(
                            new Variable("n"),
                            new Application(
                                new Variable("fac"),
                                new Plus(new Variable("n"), new Minus(new Number(1))))))))),
        new Number(value));
  }

  private static Expression infiniteList() {
    return new Recursion(new Lambda(new Variable("inf"), Cons(new Number(1), new Variable("inf"))));
  }

  private static Expression myInsert(final Expression element, final Expression list) {
    return new Application(
        new Application(
            new Recursion(
                new Lambda(
                    new Variable("insert"),
                    new Lambda(
                        new Variable("element"),
                        new Lambda(
                            new Variable("list"),
                            new ConditionalExpression(
                                new Null(new Variable("list")),
                                Cons(new Variable("element"), Nil()),
                                new ConditionalExpression(
                                    new GreaterThan(
                                        new Variable("element"), new Head(new Variable("list"))),
                                    Cons(
                                        new Head(new Variable("list")),
                                        new Application(
                                            new Application(
                                                new Variable("insert"), new Variable("element")),
                                            new Tail(new Variable("list")))),
                                    Cons(
                                        new Variable("element"),
                                        Cons(
                                            new Head(new Variable("list")),
                                            new Tail(new Variable("list")))))))))),
            element),
        list);
  }

  private static Expression mySort(final Expression list) {
    return new Application(
        new Recursion(
            new Lambda(
                new Variable("sort"),
                new Lambda(
                    new Variable("list"),
                    new ConditionalExpression(
                        new Null(new Variable("list")),
                        Nil(),
                        myInsert(
                            new Head(new Variable("list")),
                            new Application(
                                new Variable("sort"), new Tail(new Variable("list")))))))),
        list);
  }

  private static Expression myMap(final Lambda lambda, final Expression list) {
    return new Application(
        new Application(
            new Recursion(
                new Lambda(
                    new Variable("map"),
                    new Lambda(
                        new Variable("lambda"),
                        new Lambda(
                            new Variable("list"),
                            new ConditionalExpression(
                                new Null(new Variable("list")),
                                Nil(),
                                Cons(
                                    new Application(
                                        new Variable("lambda"), new Head(new Variable("list"))),
                                    new Application(
                                        new Application(
                                            new Variable("map"), new Variable("lambda")),
                                        new Tail(new Variable("list"))))))))),
            lambda),
        list);
  }

  private static Expression myTake(final Number index, final Expression list) {
    return new Application(
        new Application(
            new Recursion(
                new Lambda(
                    new Variable("take"),
                    new Lambda(
                        new Variable("index"),
                        new Lambda(
                            new Variable("list"),
                            new ConditionalExpression(
                                new LessThanOrEqual(new Variable("index"), new Number(0)),
                                Nil(),
                                new ConditionalExpression(
                                    new Null(new Variable("list")),
                                    Nil(),
                                    Cons(
                                        new Head(new Variable("list")),
                                        new Application(
                                            new Application(
                                                new Variable("take"),
                                                new Plus(
                                                    new Variable("index"),
                                                    new Minus(new Number(1)))),
                                            new Tail(new Variable("list")))))))))),
            index),
        list);
  }
}
