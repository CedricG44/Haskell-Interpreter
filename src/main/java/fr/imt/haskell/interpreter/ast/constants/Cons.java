package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;

/** Non-empty list constants. */
public final class Cons extends List {

  private final Expression head;
  private final List tail;

  static Cons getCons(Expression head, List tail) {
    return new Cons(head, tail);
  }

  private Cons(Expression head, List tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public Expression head() {
    return head;
  }

  @Override
  public List tail() {
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
  public List map(Lambda lambda) {
    return new Cons(new Application(lambda, head).reduce(), tail.map(lambda));
  }

  @Override
  public String toString() {
    return "(" + head + " : " + tail + ")";
  }

  @Override
  public String print() {
    return "(" + head.reducePrinter() + " : " + tail.reducePrinter() + ")";
  }

  public Expression reducePrinter() {
    return new Cons(head.reducePrinter(), (List) tail.reducePrinter());
  }

  @Override
  public Expression reduce() {
    return this;
  }

  @Override
  public Expression reduceByValue() {
    return this;
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
    return new Cons(head.instantiate(var, exp), (List) tail.instantiate(var, exp));
  }
}
