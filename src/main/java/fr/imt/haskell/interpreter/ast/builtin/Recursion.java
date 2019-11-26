package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Recursive built-in functions (fixpoint combinator). */
public final class Recursion extends Expression {

  private final Expression h;

  public Recursion(Expression h) {
    this.h = h;
  }

  @Override
  public Expression reduce(final Printer printer) {
    return new Application(h, this).reduce(printer);
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    // Y combinator strict
    // Y(f) = x -> f(Y(f), x)
    return new Lambda(
        new Variable("x"), new Application(new Application(h, this), new Variable("x")));
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return new Application(h, this).reduceByNeed(printer);
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Recursion(h.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "Y " + h.toString();
  }
}
