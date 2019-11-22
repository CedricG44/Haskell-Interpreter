package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Built-in constants. */
public abstract class Constant extends Expression {

  @Override
  public Expression reduce(final Printer printer) {
    return this;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return this;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return this;
  }
}
