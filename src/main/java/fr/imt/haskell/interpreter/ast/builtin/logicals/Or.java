package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** Or built-in functions. */
public final class Or extends BinaryExpression {

  public Or(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public String toString() {
    return "((|| " + expL + ") " + expR + ")";
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible();
  }

  @Override
  public Expression reduce() {
    final Expression expL = this.expL.isReducible() ? this.expL.reduce() : this.expL;
    final Expression expR = this.expR.isReducible() ? this.expR.reduce() : this.expR;
    return new Or(expL, expR).eval();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Or(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();
  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Boolean && expR instanceof Boolean)) {
      return new Or(expL, expR);
    }
    return new Boolean(((Boolean) expL).getValue() || ((Boolean) expR).getValue());
  }
}
