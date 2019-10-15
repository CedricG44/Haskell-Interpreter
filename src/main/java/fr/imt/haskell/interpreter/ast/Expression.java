package fr.imt.haskell.interpreter.ast;

/** Lambda calculus expression. */
public abstract class Expression {

  public abstract boolean isReducible();

  public abstract Expression reduce();

  public abstract boolean isApplication();

  public abstract boolean isLambda();

  public abstract boolean isVariable();

  public abstract boolean isConstant();
}
