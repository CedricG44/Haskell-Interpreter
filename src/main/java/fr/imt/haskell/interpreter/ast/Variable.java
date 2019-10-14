package fr.imt.haskell.interpreter.ast;

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
}
