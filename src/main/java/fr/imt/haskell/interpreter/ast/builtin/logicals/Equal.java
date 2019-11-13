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
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Equal(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((== " + expL + ") " + expR + ")";
  }
}
