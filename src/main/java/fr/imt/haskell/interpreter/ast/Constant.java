package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Built-in constants. */
public class Constant extends Expression {

  @Override
  public Expression reduct(final Variable var, final Expression exp) {
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
    return false;
  }

  @Override
  public boolean isConstant() {
    return true;
  }


}
