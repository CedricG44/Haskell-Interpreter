package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Unary expressions. */
public abstract class UnaryExpression extends Expression {

  protected final Expression exp;

  public UnaryExpression(Expression exp) {
    this.exp = exp;
  }
}
