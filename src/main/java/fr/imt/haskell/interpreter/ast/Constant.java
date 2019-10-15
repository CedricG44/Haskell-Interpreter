package fr.imt.haskell.interpreter.ast;

/** Built-in constants. */
public class Constant extends Expression {

  @Override
  public boolean isReducible() {
    return false;
  }

  @Override
  public Expression reduce() {
    return this;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return this;
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
