package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.NOT;

public final class Not extends UnaryExpression {

  public Not(Expression expression) {
    super(NOT, expression);
  }

  @Override
  public Expression eval() {
    if (!(exp instanceof Boolean)) {
      return new Not(exp);
    }
    return new Boolean(!((Boolean) exp).getValue());
  }

  @Override
  public String toString() {
    return "(! " + exp + ")";
  }
}
