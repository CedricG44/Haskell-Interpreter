package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Lambda calculus expression. */
public abstract class Expression {

  public Expression reduce(final Printer printer) {
    throw new UnsupportedOperationException("Weak Head Normal Form !");
  }

  public Expression reduceByValue(final Printer printer) {
    throw new UnsupportedOperationException("Weak Head Normal Form !");
  }

  public Expression reduceByNeed(final Printer printer) {
    throw new UnsupportedOperationException("Weak Head Normal Form !");
  }

  public Expression reducePrinter(final Printer printer) {
    return this;
  }

  public abstract Expression instantiate(final Variable var, final Expression exp);
}
