package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

import java.util.Objects;

/** Lambda abstractions. */
public final class Lambda extends Expression {

  private Variable var;
  private Expression exp;

  public Lambda(Variable var, Expression exp) {
    this.var = var;
    this.exp = exp;
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
  public Expression reduct(final Variable var, final Expression exp) {
    if (this.var.equals(var)) {
      return this.exp.reduct(var, exp);
    } else {
      // TODO
      return this;
    }
  }

  @Override
  public void accept(final Visitor visitor) {
    visitor.visit(this);
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
