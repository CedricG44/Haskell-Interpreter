package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Indirection wrapper for call by need. */
public class Indirection extends Expression {

  private Expression exp;

  public Indirection(Expression exp) {
    this.exp = exp;
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    exp = exp.reduceByNeed(printer);
    return exp;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return this;
  }

  @Override
  public String toString() {
    return exp.toString();
  }
}
