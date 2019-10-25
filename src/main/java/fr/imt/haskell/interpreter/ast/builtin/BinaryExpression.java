package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Binary expressions. */
public abstract class BinaryExpression extends Expression {

  protected Expression expL;
  protected Expression expR;

  public BinaryExpression(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  abstract public Expression eval();
}
