package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Plus built-in functions. */
public final class Plus extends BinaryExpression {

  public Plus(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Plus] Reduction step: " + this);
    return new Number(((Number) expL.reduce()).getValue() + ((Number) expR.reduce()).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Plus(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((+ " + expL + ") " + expR + ")";
  }
}
