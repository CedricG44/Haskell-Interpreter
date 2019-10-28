package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** Or built-in functions. */
public final class Or extends BinaryExpression {

  public Or(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    return new Boolean(
        ((Boolean) expL.reduce()).getValue() || ((Boolean) expR.reduce()).getValue());
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Or(expL.substitute(var, substitute), expR.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "((|| " + expL + ") " + expR + ")";
  }
}
