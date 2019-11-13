package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Minus built-in functions. */
public final class Minus extends UnaryExpression {

  public Minus(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Minus] Reduction step: " + this);
    return new Number(-((Number) exp.reduce()).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Minus(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(- " + exp + ")";
  }
}
