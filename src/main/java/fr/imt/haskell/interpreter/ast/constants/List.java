package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;

import static fr.imt.haskell.interpreter.ast.constants.Cons.getCons;
import static fr.imt.haskell.interpreter.ast.constants.Nil.getNil;

/** List constants. */
public abstract class List extends Constant {

  public static List Cons(Expression head, List tail) {
    return getCons(head, tail);
  }

  public static List Nil() {
    return getNil();
  }

  public abstract Expression head();

  public abstract List tail();

  public abstract boolean isEmpty();

  public abstract int length();

  public abstract List map(Lambda lambda);
}
