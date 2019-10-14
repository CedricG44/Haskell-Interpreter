package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

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
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
