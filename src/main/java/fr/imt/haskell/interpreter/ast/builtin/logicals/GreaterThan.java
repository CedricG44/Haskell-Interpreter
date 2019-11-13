package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Greater than built-in functions. */
public class GreaterThan extends BinaryExpression {

  public GreaterThan(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    System.out.println("[GreaterThan] Reduction step: " + this);
    return new Boolean(((Number) expL.reduce()).getValue() > ((Number) expR.reduce()).getValue());
  }

  @Override
  public Expression reduceByValue() {
    System.out.println("[GreaterThan] Reduction step: " + this);
    return new Boolean(
        ((Number) expL.reduceByValue()).getValue() > ((Number) expR.reduceByValue()).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new GreaterThan(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((> " + expL + ") " + expR + ")";
  }
}
