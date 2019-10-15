package fr.imt.haskell.interpreter;

import fr.imt.haskell.interpreter.ast.*;
import fr.imt.haskell.interpreter.ast.constants.*;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.visitor.BetaReductionVisitor;
import fr.imt.haskell.interpreter.ast.visitor.PrinterVisitor;
import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Main. */
public class HaskellInterpreter {

  public static void main(String[] args) {
    System.out.println("HaskellInterpreter !\n");

    final Visitor printer = new PrinterVisitor();
    final BetaReductionVisitor betaReductionVisitor = new BetaReductionVisitor();

    final Application application1 =
        new Application(
            new Lambda(
                new Variable("x"),
                new Application(new Application(new Plus(), new Variable("x")), new Variable("x"))),
            new Number(5));

    System.out.println("\nExpression to evaluate: " + application1 + "\n");
    application1.accept(printer);
    application1.accept(betaReductionVisitor);
    System.out.println("\nReducted expression: " + betaReductionVisitor.getExp() + "\n");

    final Application application2 =
        new Application(
            new Lambda(
                new Variable("z"),
                new Application(
                    new Application(
                        new Plus(),
                        new Application(
                            new Lambda(
                                new Variable("y"), new Application(new Minus(), new Variable("y"))),
                            new Number(5))),
                    new Variable("z"))),
            new Number(42));

    //System.out.println("\nExpression to evaluate: " + application2 + "\n");
    //application2.accept(printer);
  }
}
