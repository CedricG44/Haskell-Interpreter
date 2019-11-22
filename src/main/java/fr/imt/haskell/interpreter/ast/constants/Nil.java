package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Empty list constants. */
public final class Nil extends List {

  static Nil getNil() {
    return new Nil();
  }

  private Nil() {}

  @Override
  public Expression head() {
    throw new UnsupportedOperationException("Empty list.");
  }

  @Override
  public List tail() {
    throw new UnsupportedOperationException("Empty list.");
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public int length() {
    return 0;
  }

  @Override
  public String toString() {
    return "[]";
  }

  @Override
  public Expression reduce(final Printer printer) {
    return this;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return this;
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
    return this;
  }
}
