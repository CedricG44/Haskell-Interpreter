package fr.imt.haskell.interpreter;

import fr.imt.haskell.interpreter.ast.*;
import fr.imt.haskell.interpreter.ast.constants.*;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.visitor.BetaReductionVisitor;
import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

    final Application application1 =
        new Application(
            new Lambda(
                new Variable("x"),
                new Application(new Application(new Plus(), new Variable("x")), new Variable("x"))),
            new Number(5));

    System.out.println("Expression to evaluate: " + application1 + "\n");

    final Visitor visitor = new BetaReductionVisitor();
    application1.accept(visitor);
  }
}
