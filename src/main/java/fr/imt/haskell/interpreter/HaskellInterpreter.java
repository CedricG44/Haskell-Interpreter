package fr.imt.haskell.interpreter;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.ConditionalExpression;
import fr.imt.haskell.interpreter.ast.builtin.Recursion;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Equal;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Minus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Plus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Times;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

    reduce(
        new Application(
            new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
            new Number(5)));

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

    reduce(new Equal(new Minus(new Minus(new Number(5))), new Number(5)));

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

    reduce(new Application(new Recursion(factorial), new Number(1)));
  }

  public static void reduce(final Expression exp) {
    System.out.println("\nExpression to reduce: " + exp + "\n");
    System.out.println("\nReduced expression: " + exp.reduce() + "\n");
  }
}
