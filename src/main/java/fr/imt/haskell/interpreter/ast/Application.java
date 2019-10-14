package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Applications. */
public final class Application extends Expression {

  private Expression expL;
  private Expression expR;

  public Application(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  public Expression getExpL() {
    return expL;
  }

  public Expression getExpR() {
    return expR;
  }

  @Override
  public String toString() {
    return "(" + expL + " " + expR + ")";
  }

  @Override
  public void accept(final Visitor visitor) {
    visitor.visit(this);
  }
}
