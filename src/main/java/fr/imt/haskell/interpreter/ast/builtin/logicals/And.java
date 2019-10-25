package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.AND;

/** And built-in functions. */
public final class And extends BinaryExpression {

  public And(Expression expL, Expression expR) {
    super(AND, expL, expR);
  }

  public Expression eval() {
    if (!(expL instanceof Boolean && expR instanceof Boolean)) {
      return new And(expL, expR);
    }
    return new Boolean(((Boolean) expL).getValue() && ((Boolean) expR).getValue());
  }

  @Override
  public String toString() {
    return "((&& " + expL + ") " + expR + ")";
  }
}
