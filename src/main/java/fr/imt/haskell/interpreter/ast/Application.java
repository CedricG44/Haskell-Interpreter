package fr.imt.haskell.interpreter.ast;

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
}
