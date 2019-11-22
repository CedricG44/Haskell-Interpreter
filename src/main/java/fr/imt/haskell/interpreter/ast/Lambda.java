package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

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
  public Expression reduce(final Printer printer) {
    return this;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return this;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    if (this.var.equals(var)) {
      return this;
    }
    return new Lambda(this.var, this.exp.instantiate(var, exp));
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
