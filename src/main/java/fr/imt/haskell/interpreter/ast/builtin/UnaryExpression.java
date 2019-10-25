package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Unary expressions. */
public abstract class UnaryExpression extends Expression {

  protected Expression expression;

  public UnaryExpression(Expression expression) {
    this.expression = expression;
  }

  @Override
  public boolean isReducible() {
    return expression.isReducible();
  }

  @Override
  public Expression reduce() {
    return isReducible() ? expression.reduce() : this;
  }
}
