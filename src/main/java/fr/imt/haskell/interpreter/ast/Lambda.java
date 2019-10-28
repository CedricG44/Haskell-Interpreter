package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Lambda abstractions. */
public final class Lambda extends Expression {

  private final Variable var;
  private final Expression exp;

  public Lambda(Variable var, Expression exp) {
    this.var = var;
    this.exp = exp;
  }

  @Override
  public Expression reduce() {
    return this;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    System.out.println("[Lambda] Reduction step:  " + this);
    if (this.var.equals(var)) {
      return this;
    }
    return new Lambda(this.var, exp.substitute(var, substitute));
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
}
