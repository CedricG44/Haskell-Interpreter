package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** Not built-in functions. */
public final class Not extends UnaryExpression {

  public Not(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Not] Reduction step: " + this);
    return new Boolean(!((Boolean) exp.reduce()).getValue());
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Not(exp.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "(not " + exp + ")";
  }
}
