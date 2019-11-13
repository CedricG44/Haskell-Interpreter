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
    System.out.println("[Or] Reduction step: " + this);
    return new Boolean(
        ((Boolean) expL.reduce()).getValue() || ((Boolean) expR.reduce()).getValue());
  }

  @Override
  public Expression reduceByValue() {
    System.out.println("[Or] Reduction step: " + this);
    return new Boolean(
        ((Boolean) expL.reduceByValue()).getValue() || ((Boolean) expR.reduceByValue()).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Or(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((|| " + expL + ") " + expR + ")";
  }
}
