package fr.imt.haskell.interpreter.ast;

/** Built-in constants. */
public class Constant extends Expression {

  @Override
  public Expression reduce() {
    System.out.println("[Constant] Reduction step:  " + this);
    return this;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return this;
  }
}
