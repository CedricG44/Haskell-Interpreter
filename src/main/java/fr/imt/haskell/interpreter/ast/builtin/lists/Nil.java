package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Constant;

/** Empty list constants. */
public final class Nil<T extends Constant> extends List<T> {

  static <T extends Constant> Nil<T> getNil() {
    return new Nil<>();
  }

  private Nil() {}

  @Override
  public T head() {
    throw new UnsupportedOperationException("Empty list.");
  }

  @Override
  public List<T> tail() {
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
}
