package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Non-empty list constants. */
public final class Cons extends List {

  private final Expression head;
  private final Expression tail;

  static Cons getCons(Expression head, Expression tail) {
    return new Cons(head, tail);
  }

  private Cons(Expression head, Expression tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public Expression head() {
    return head;
  }

  @Override
  public Expression tail() {
    return tail;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public int length() {
    return 1 + ((List) tail).length();
  }

  @Override
  public String toString() {
    return "(" + head + " : " + tail + ")";
  }

  @Override
  public Expression reduce(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Cons(head.reduce(printer), tail.reduce(printer));
    } else {
      return this;
    }
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Cons(head.reduceByValue(printer), tail.reduceByValue(printer));
    } else {
      return this;
    }
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Cons(head.reduceByNeed(printer), tail.reduceByNeed(printer));
    } else {
      return this;
    }
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
    return new Cons(head.instantiate(var, exp), tail.instantiate(var, exp));
  }
}
