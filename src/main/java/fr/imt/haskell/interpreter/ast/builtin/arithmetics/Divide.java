package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Divide built-in functions. */
public final class Divide extends BinaryExpression {

  public Divide(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Divide] Reduction step: " + this);
    return new Number(((Number) expL.reduce()).getValue() / ((Number) expR.reduce()).getValue());
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Divide(expL.substitute(var, substitute), expR.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "((/ " + expL + ") " + expR + ")";
  }
}
