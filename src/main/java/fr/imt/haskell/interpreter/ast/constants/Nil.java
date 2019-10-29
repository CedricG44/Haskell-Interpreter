package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;

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
  public List map(Lambda lambda) {
    return this;
  }

  @Override
  public String toString() {
    return "[]";
  }
}
