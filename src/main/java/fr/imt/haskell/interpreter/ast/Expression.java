package fr.imt.haskell.interpreter.ast;

/** Lambda calculus expression. */
public abstract class Expression {

  public abstract boolean isReducible();

  public abstract Expression reduce();

  public abstract Expression substitute(final Variable var, final Expression substitute);
}
