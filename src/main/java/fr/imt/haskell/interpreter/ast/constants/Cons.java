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
    return this;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return this;
  }

  @Override
  public Expression reducePrinter(final Printer printer) {
    return new Cons(head.reducePrinter(printer), tail.reducePrinter(printer));
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
    return new Cons(head.instantiate(var, exp), tail.instantiate(var, exp));
  }
}
