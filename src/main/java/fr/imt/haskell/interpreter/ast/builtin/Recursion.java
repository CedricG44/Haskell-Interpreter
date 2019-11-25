package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

/** Recursive built-in functions (fixpoint combinator). */
public final class Recursion extends Expression {

  private final Expression h;

  public Recursion(Expression h) {
    this.h = h;
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Application(h, this).reduce(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    // Y combinator strict
    // Y(f) = x -> f(Y(f), x)
    final String oldExp = toString();
    final Expression newExp =
        new Lambda(new Variable("x"), new Application(new Application(h, this), new Variable("x")));
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Application(h, this).reduceByNeed(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
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
