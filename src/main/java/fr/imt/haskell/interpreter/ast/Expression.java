package fr.imt.haskell.interpreter.ast;

/** Lambda calculus expression. */
public abstract class Expression {

  public Expression reduce() {
    throw new UnsupportedOperationException("Weak Head Normal Form !");
  }

  public Expression reduceByValue() {
    throw new UnsupportedOperationException("Weak Head Normal Form !");
  }

  public abstract Expression instantiate(final Variable var, final Expression exp);
}
