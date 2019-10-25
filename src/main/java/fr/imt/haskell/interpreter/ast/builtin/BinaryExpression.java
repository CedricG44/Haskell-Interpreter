package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;

/** Binary expressions. */
public abstract class BinaryExpression extends Application {

  public BinaryExpression(Expression expL, Expression expR) {
    super(expL, expR);
  }

  public abstract Expression eval();
}
