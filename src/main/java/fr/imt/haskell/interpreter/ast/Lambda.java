package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Lambda abstractions. */
public final class Lambda extends Expression {

  private Variable var;
  private Expression exp;

  public Lambda(Variable var, Expression exp) {
    this.var = var;
    this.exp = exp;
  }

  @Override
  public boolean isReducible() {
    return this.exp.isReducible();
  }

  @Override
  public Expression reduce() {
    if (isReducible()) {
      return new Lambda(var, this.exp.reduce());
    }
    return this;
  }

  public Variable getVar() {
    return var;
  }

  public Expression getExp() {
    return exp;
  }

  @Override
  public String toString() {
    return "(\\" + var + " -> " + exp + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Lambda lambda = (Lambda) o;
    return var.equals(lambda.var) && exp.equals(lambda.exp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(var, exp);
  }

  @Override
  public boolean isApplication() {
    return false;
  }

  @Override
  public boolean isLambda() {
    return true;
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
