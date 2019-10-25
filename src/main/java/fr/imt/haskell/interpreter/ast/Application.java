package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Applications. */
public class Application extends Expression {

  protected final Expression expL;
  protected final Expression expR;

  public Application(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible() || expL instanceof Lambda;
  }

  @Override
  public Expression reduce() {
    Expression exp = this;
    System.out.println("[Application] Reduction step: " + exp);

    if (expL.isReducible()) {
      exp = new Application(expL.reduce(), expR);
    } else if (expR.isReducible()) {
      exp = new Application(expL, expR.reduce());
    } else if (expL instanceof Lambda) {
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
}
