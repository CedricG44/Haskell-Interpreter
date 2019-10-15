package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.constants.Number;

import java.util.Objects;

/** Applications. */
public final class Application extends Expression {

  private Expression expL;
  private Expression expR;

  public Application(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  public Expression getExpL() {
    return expL;
  }

  public Expression getExpR() {
    return expR;
  }

  @Override
  public String toString() {
    return "(" + expL + " " + expR + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Application that = (Application) o;
    return expL.equals(that.expL) && expR.equals(that.expR);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expL, expR);
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible() || expL.isLambda();
  }

  @Override
  public Expression reduce() {
    Expression exp = new Number(666);

    if (expL.isReducible()) {
      exp = new Application(expL.reduce(), expR);
    } else if (expR.isReducible()) {
      exp = new Application(expL, expR.reduce());
    } else if (expL.isLambda()) {
      Lambda lambda = (Lambda) expL;
      exp = lambda.getExp().substitute(lambda.getVar(), expR);
    }

    if (exp.isReducible()) {
      return exp.reduce();
    }

    return exp;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Application(expL.substitute(var, substitute), expR.substitute(var, substitute));
  }

  @Override
  public boolean isApplication() {
    return true;
  }

  @Override
  public boolean isLambda() {
    return false;
  }

  @Override
  public boolean isVariable() {
    return false;
  }

  @Override
  public boolean isConstant() {
    return false;
  }
}
