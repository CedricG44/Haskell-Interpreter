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
import fr.imt.haskell.interpreter.ast.builtin.lists.Map;
import fr.imt.haskell.interpreter.ast.builtin.lists.Null;
import fr.imt.haskell.interpreter.ast.builtin.lists.Tail;
import fr.imt.haskell.interpreter.ast.builtin.logicals.Equal;
import fr.imt.haskell.interpreter.ast.builtin.logicals.GreaterThan;
import fr.imt.haskell.interpreter.ast.builtin.logicals.LessThanOrEqual;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import static fr.imt.haskell.interpreter.ast.constants.List.Cons;
import static fr.imt.haskell.interpreter.ast.constants.List.Nil;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

//    reduce(
//        new Application(
//            new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
//            new Plus(new Number(5), new Number(2))));
    /*

    reduce(
        new Application(
            new Lambda(
                new Variable("z"),
                new Plus(
                    new Application(
                        new Lambda(new Variable("y"), new Minus(new Variable("y"))), new Number(5)),
                    new Variable("z"))),
            new Number(42)));

    reduce(
        new Application(
            new Application(
                new Lambda(new Variable("x"), new Variable("x")),
                new Lambda(new Variable("y"), new Variable("y"))),
            new Lambda(new Variable("z"), new Variable("z"))));

    reduce(new Equal(new Minus(new Minus(new Number(5))), new Number(5)));*/

    final Expression factorial =
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
                            new Plus(new Variable("n"), new Minus(new Number(1))))))));

    /*reduce(new Application(new Recursion(factorial), new Number(1)));*/

    final List list =
        Cons(new Number(1), Cons(new Number(2), Cons(new Number(4), Cons(new Number(5), Nil()))));

    final List listUnordered =
        Cons(new Number(2), Cons(new Number(1), Cons(new Number(5), Cons(new Number(4), Nil()))));

    /*    reduce(new Head(list));
    reduce(new Tail(list));
    reduce(new Null(list));
    reduce(new Head(list));
    reduce(new Length(list));
    reduce(new Equal(new Head(list), new Number(1)));*/
    //    reduce(
    //        new Map(list, new Lambda(new Variable("x"), new Plus(new Variable("x"), new
    // Number(1)))));

    // TODO: find the trick
    //    reduce(new Map(list, new Plus(new Variable("x"), new Number(1))));

    /*    final List<List<Number>> listList =
        Cons(
            Cons(new Number(1), Cons(new Number(2), Nil())),
            Cons(Cons(new Number(3), Nil()), Nil()));

    reduce(new Head(listList));
    reduce(new Tail(listList));
    reduce(new Length(listList));*/

    Expression exp1 = Cons(new Plus(new Number(1), new Number(2)), Nil());
    Expression exp2 = infiniteList();
    Expression exp3 = insert(new Number(3), list);
    Expression exp4 =
        map(new Lambda(new Variable("x"), new Plus(new Variable("x"), new Number(1))), list);
    // TODO: fix
    Expression exp5 = sort(listUnordered);
    Expression exp6 = take(new Number(2), list);
    Expression exp7 = take(new Number(3), infiniteList());

    Expression exp =
        new Application(
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
            new Number(5));

        reduce(exp3);
  }

  private static Expression infiniteList() {
    return new Recursion(new Lambda(new Variable("inf"), Cons(new Number(1), new Variable("inf"))));
  }

  public static void reduce(final Expression exp) {
    System.out.println("\nExpression to reduce: " + exp + "\n");
    System.out.println(exp);

    final Expression result = exp.reduceByNeed(new Printer(exp));
    System.out.println("Résultat: " + result);

    //    System.out.println(
    //        "\nReduced expression with printer: " + exp.reducePrinter(new Printer(exp)) + "\n");
  }

  private static Expression insert(final Expression element, final Expression list) {
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

  private static Expression sort(final Expression list) {
    return new Application(
        new Recursion(
            new Lambda(
                new Variable("sort"),
                new Lambda(
                    new Variable("list"),
                    new ConditionalExpression(
                        new Null(new Variable("list")),
                        Nil(),
                        insert(
                            new Head(new Variable("list")),
                            new Application(
                                new Variable("sort"), new Tail(new Variable("list")))))))),
        list);
  }

  private static Expression map(final Lambda lambda, final Expression list) {
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

  private static Expression take(final Number index, final Expression list) {
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
