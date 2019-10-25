package fr.imt.haskell.interpreter;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.constants.Minus;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.constants.Plus;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

    final Application application1 =
        new Application(
            new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
            new Number(5));

    System.out.println("\nExpression to reduce: " + application1 + "\n");
    System.out.println("\nReduced expression: " + application1.reduce() + "\n");

    final Application application2 =
            new Application(
                    new Lambda(
                            new Variable("z"),
                            new Application(
                                    new Plus(
                                            new Lambda(
                                                    new Variable("y"), new Minus(new Variable("y"))),
                                            new Number(5)),
                                    new Variable("z"))),
                    new Number(42));
    System.out.println("\nExpression to reduce: " + application2 + "\n");
    System.out.println("\nReduced expression: " + application2.reduce() + "\n");
    /*
    final Application application3 =
        new Application(
            new Application(
                new Lambda(new Variable("x"), new Variable("x")),
                new Lambda(new Variable("y"), new Variable("y"))),
            new Lambda(new Variable("z"), new Variable("z")));

    System.out.println("\nExpression to reduce: " + application3 + "\n");
    System.out.println("\nReduced expression: " + application3.reduce() + "\n");

    final Application application4 =
        new Application(
            new Lambda(
                new Variable("z"),
                new Application(
                    new Application(
                        new Plus(),
                        new Application(
                            new Lambda(
                                new Variable("z"), new Application(new Minus(), new Variable("z"))),
                            new Number(5))),
                    new Variable("z"))),
            new Number(42));

    System.out.println("\nExpression to reduce: " + application4 + "\n");
    System.out.println("\nReduced expression: " + application4.reduce() + "\n");*/
  }
}
