package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

/** Recursive built-in functions (fixpoint combinator). */
public final class Recursion extends Expression {

  private final Expression h;

  public Recursion(Expression h) {
    this.h = h;
  }

  @Override
  public Expression reduce() {
    System.out.println("[Recursion] Before reduction step:  " + this);
    Expression reduce = new Application(h, this).reduce();
    System.out.println("[Recursion] After reduction step:  " + this);
    return reduce;
  }

  @Override
  public Expression reduceByValue() {
    return new Application(h, this).reduceByValue();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Recursion(h.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return h.toString();
  }
}
