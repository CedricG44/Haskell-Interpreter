package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Variable names. */
public final class Variable extends Expression {

  private final String value;

  public Variable(String value) {
    this.value = value;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    if (this.equals(var)) {
      return exp;
    }
    return this;
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
}
