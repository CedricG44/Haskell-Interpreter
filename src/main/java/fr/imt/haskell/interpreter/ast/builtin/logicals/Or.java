package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.OR;

/** Or built-in functions. */
public final class Or extends BinaryExpression {

  public Or(Expression expL, Expression expR) {
    super(OR, expL, expR);
  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Boolean && expR instanceof Boolean)) {
      return new Or(expL, expR);
    }
    return new Boolean(((Boolean) expL).getValue() || ((Boolean) expR).getValue());
  }

  @Override
  public String toString() {
    return "((|| " + expL + ") " + expR + ")";
  }
}
