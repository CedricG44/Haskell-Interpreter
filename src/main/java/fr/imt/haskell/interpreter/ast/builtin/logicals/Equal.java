package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** Equal built-in functions. */
public final class Equal extends BinaryExpression {

  public Equal(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Equal] Reduction step: " + this);
    return new Boolean(expL.reduce().equals(expR.reduce()));
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Equal(expL.substitute(var, substitute), expR.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "((== " + expL + ") " + expR + ")";
  }
}
