package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

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
    return expL.equals(that.expL) &&
            expR.equals(that.expR);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expL, expR);
  }

  @Override
  public Expression reduct(final Variable var, final Expression exp) {
    return new Application(expL.reduct(var, exp), expR.reduct(var, exp));
  }

  @Override
  public void accept(final Visitor visitor) {
    visitor.visit(this);
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
