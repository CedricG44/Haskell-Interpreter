package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.TIMES;

/** Times built-in functions. */
public final class Times extends BinaryExpression {

  public Times(Expression expL, Expression expR) {
    super(TIMES, expL, expR);
  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Times(expL, expR);
    }
    return new Number(((Number) expL).getValue() * ((Number) expR).getValue());
  }

  @Override
  public String toString() {
    return "((* " + expL + ") " + expR + ")";
  }
}
