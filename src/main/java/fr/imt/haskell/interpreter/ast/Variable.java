package fr.imt.haskell.interpreter.ast;

/** Variable names. */
public final class Variable extends Expression {

  private final String value;

  public Variable(String value) {
    this.value = value;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    if (value.equals(var.getValue())) {
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
}
