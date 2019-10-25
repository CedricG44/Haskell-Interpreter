package fr.imt.haskell.interpreter.ast;

import java.util.Objects;

/** Variable names. */
public final class Variable extends Expression {

  private final String value;

  public Variable(String value) {
    this.value = value;
  }

  @Override
  public boolean isReducible() {
    return false;
  }

  @Override
  public Expression reduce() {
    System.out.println("[Variable] Reduction step: " + this);
    return this;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    if (var.equals(this)) {
      return substitute;
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
