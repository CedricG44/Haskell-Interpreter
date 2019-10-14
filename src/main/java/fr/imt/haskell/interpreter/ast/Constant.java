package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Built-in constants. */
public class Constant extends Expression {

  @Override
  public void accept(final Visitor visitor) {
    visitor.visit(this);
  }
}
