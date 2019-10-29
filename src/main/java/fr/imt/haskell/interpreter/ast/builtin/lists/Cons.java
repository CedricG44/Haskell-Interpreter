package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Constant;

/** Non-empty list constants. */
public final class Cons<T extends Constant> extends List<T> {

  private final T head;
  private final List<T> tail;

  static <T extends Constant> Cons<T> getCons(T head, List<T> tail) {
    return new Cons<>(head, tail);
  }

  private Cons(T head, List<T> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public T head() {
    return head;
  }

  @Override
  public List<T> tail() {
    return tail;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int length() {
    return 1 + tail.length();
  }

  @Override
  public String toString() {
    return "(" + head + " : " + tail + ")";
  }
}
