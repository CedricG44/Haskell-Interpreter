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
    return new Number(((Number) expL.reduce()).getValue() + ((Number) expR.reduce()).getValue());
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Plus(expL.substitute(var, substitute), expR.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "((+ " + expL + ") " + expR + ")";
  }
}
