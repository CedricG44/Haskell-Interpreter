package fr.imt.haskell.interpreter.ast;

/** Built-in constants. */
public abstract class Constant extends Expression {

  @Override
  public Expression reduce() {
    System.out.println("[Constant] Reduction step:  " + this);
    return this;
  }

  @Override
  public Expression reduceByValue() {
    return this;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return this;
  }
}
