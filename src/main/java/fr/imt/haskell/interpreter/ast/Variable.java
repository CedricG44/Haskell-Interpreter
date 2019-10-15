package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

import java.util.Objects;

/** Variable names. */
public final class Variable extends Expression {

  private String value;

  public Variable(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Variable variable = (Variable) o;
    return value.equals(variable.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public Expression reduct(final Variable var, final Expression exp) {
    if (this.equals(var)) {
      return exp;
    }

    return this;
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
    return false;
  }

  @Override
  public boolean isVariable() {
    return true;
  }

  @Override
  public boolean isConstant() {
    return false;
  }
}
