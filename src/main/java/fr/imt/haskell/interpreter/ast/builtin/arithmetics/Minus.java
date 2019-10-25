package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Minus built-in functions. */
public final class Minus extends UnaryExpression {

  public Minus(Expression expression) {
    super(expression);
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Minus(exp.substitute(var, substitute)).eval();
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
