package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** Times built-in functions. */
public final class Times extends BinaryExpression {

  public Times(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public String toString() {
    return "((* " + expL + ") " + expR + ")";
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible();
  }

  @Override
  public Expression reduce() {
    final Expression expL = this.expL.isReducible() ? this.expL.reduce() : this.expL;
    final Expression expR = this.expR.isReducible() ? this.expR.reduce() : this.expR;
    return new Times(expL, expR).eval();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Times(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();

  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Times(expL, expR);
    }
    return new Number(((Number) expL).getValue() * ((Number) expR).getValue());
  }
}