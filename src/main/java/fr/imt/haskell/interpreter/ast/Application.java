package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Applications. */
public final class Application extends Expression {

  private final Expression expL;
  private final Expression expR;

  public Application(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  @Override
  public Expression reduce() {
    System.out.println("[Application] Reduction step: " + this);
    Lambda lambda = (Lambda) expL.reduce();
    return lambda.getExp().substitute(lambda.getVar(), expR).reduce();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Application(expL.substitute(var, substitute), expR.substitute(var, substitute));
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
