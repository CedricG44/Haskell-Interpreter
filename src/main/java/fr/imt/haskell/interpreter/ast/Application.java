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

  public void setExpL(Expression expL) {
    this.expL = expL;
  }

  public void setExpR(Expression expR) {
    this.expR = expR;
  }

  @Override
  public String toString() {
    return "(" + expL + " " + expR + ")";
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(expL);
    visitor.visit(expR);
  }
}
