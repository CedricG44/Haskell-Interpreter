package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

/** Unary expressions. */
public abstract class UnaryExpression extends Expression {

  protected final Expression exp;

  public UnaryExpression(Expression exp) {
    this.exp = exp;
  }

  @Override
  public boolean isReducible() {
    return exp.isReducible();
  }

  @Override
  public Expression reduce() {
    System.out.println("[UnaryExpression] Reduction step: " + this);
    return isReducible() ? exp.reduce() : eval();
  }

  public abstract Expression eval();

  public Expression getExp() {
    return exp;
  }
}
