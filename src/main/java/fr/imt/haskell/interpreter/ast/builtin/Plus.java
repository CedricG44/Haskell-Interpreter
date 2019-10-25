package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

/** Plus built-in functions. */
public final class Plus extends BinaryExpression {

  public Plus(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public String toString() {
    return "((+ " + expL.toString() + ") " + expR.toString() + ")";
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible();
  }

  @Override
  public Expression reduce() {
    final Expression expL = this.expL.isReducible() ? this.expL.reduce() : this.expL;
    final Expression expR = this.expR.isReducible() ? this.expR.reduce() : this.expR;
    return new Plus(expL, expR);
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    final Expression expL = this.expL.substitute(var, substitute);
    final Expression expR = this.expR.substitute(var, substitute);
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Plus(expL, expR);
    }
    return new Number(((Number) expL).getValue() + ((Number) expR).getValue());
  }
}
