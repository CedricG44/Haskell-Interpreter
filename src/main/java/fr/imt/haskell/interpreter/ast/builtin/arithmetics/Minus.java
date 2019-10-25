package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.MINUS;

/** Minus built-in functions. */
public final class Minus extends UnaryExpression {

  public Minus(Expression expression) {
    super(MINUS, expression);
  }

  @Override
  public Expression eval() {
    if (!(exp instanceof Number)) {
      return new Minus(exp);
    }
    return new Number(-((Number) exp).getValue());
  }

  @Override
  public String toString() {
    return "(- " + exp + ")";
  }
}
