package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Constant;

import static fr.imt.haskell.interpreter.ast.builtin.lists.Cons.getCons;
import static fr.imt.haskell.interpreter.ast.builtin.lists.Nil.getNil;

/** List constants. */
public abstract class List<T extends Constant> extends Constant {

  public static <T extends Constant> List<T> Cons(T head, List<T> tail) {
    return getCons(head, tail);
  }

  public static <T extends Constant> List<T> Nil() {
    return getNil();
  }

  public abstract T head();

  public abstract List<T> tail();

  public abstract boolean isEmpty();

  public abstract int length();
}
