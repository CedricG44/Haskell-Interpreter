package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.EQUAL;

/** Equal built-in functions. */
public final class Equal extends BinaryExpression {

  public Equal(Expression expL, Expression expR) {
    super(EQUAL, expL, expR);
  }

  @Override
  public Expression eval() {
    return new Boolean(expL.equals(expR));
  }

  @Override
  public String toString() {
    return "((== " + expL + ") " + expR + ")";
  }
}
