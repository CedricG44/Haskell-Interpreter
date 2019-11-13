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
    return new Application(h, this).reduce();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    System.out.println("[Recursion] Reduction step:  " + this);
    return new Recursion(h.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return h.toString();
  }
}
