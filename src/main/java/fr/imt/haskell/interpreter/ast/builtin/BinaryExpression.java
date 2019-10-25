package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Binary expressions. */
public abstract class BinaryExpression extends Expression {

  public BinaryExpression(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  protected Expression expL;
  protected Expression expR;
}
