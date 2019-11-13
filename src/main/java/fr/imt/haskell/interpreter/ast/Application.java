package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.builtin.Recursion;

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
    return lambda.getExp().instantiate(lambda.getVar(), expR).reduce();
  }

  @Override
  public Expression reduceByValue() {
    System.out.println("[Application] Before reduction step: " + this);
    Lambda lambda = (Lambda) expL.reduceByValue();
    Expression expression = lambda.getExp().instantiate(lambda.getVar(), expR.reduceByValue());
    System.out.println("[Application] After reduction step: " + this);
    return expression.reduceByValue();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Application(expL.instantiate(var, exp), expR.instantiate(var, exp));
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
