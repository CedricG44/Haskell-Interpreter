package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.DIVIDE;

/** Divide built-in functions. */
public final class Divide extends BinaryExpression {

  public Divide(Expression expL, Expression expR) {
    super(DIVIDE, expL, expR);
  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Divide(expL, expR);
    }
    return new Number(((Number) expL).getValue() / ((Number) expR).getValue());
  }

  @Override
  public String toString() {
    return "((/ " + expL + ") " + expR + ")";
  }
}
