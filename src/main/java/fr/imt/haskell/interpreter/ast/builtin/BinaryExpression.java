package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Binary expressions. */
public abstract class BinaryExpression extends Expression {

  protected final Expression expL;
  protected final Expression expR;

  public BinaryExpression(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  public abstract Expression eval();
}
