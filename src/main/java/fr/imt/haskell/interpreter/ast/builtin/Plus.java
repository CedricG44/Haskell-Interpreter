package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

/** Plus built-in functions. */
public final class Plus extends BinaryExpression {

  private Expression left;
  private Expression right;

  public Plus(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "((+ " + left.toString() + ") " + right.toString() + ")";
  }

  @Override
  public boolean isReducible() {
    return left.isReducible() || right.isReducible();
  }

  @Override
  public Expression reduce() {
    Expression expL = left.isReducible() ? left.reduce() : left;
    Expression expR = right.isReducible() ? right.reduce() : right;
    return new Plus(expL, expR);
  }

  @Override
  public Expression substitute(Variable var, Expression substitute) {
    Expression expL = left.substitute(var, substitute);
    Expression expR = right.substitute(var, substitute);
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Plus(expL, expR);
    }
    return new Number(((Number) expL).getValue() + ((Number) expR).getValue());
  }
}
