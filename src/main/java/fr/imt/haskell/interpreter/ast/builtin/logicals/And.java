package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** And built-in functions. */
public final class And extends BinaryExpression {

  public And(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    return new Boolean(
        ((Boolean) expL.reduce()).getValue() && ((Boolean) expR.reduce()).getValue());
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new And(this.expL.substitute(var, substitute), this.expR.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "((&& " + expL + ") " + expR + ")";
  }
}
