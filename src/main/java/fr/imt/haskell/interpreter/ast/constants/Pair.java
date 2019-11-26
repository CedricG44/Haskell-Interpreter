package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.Objects;

public class Pair extends Constant {

  private final Expression left;
  private final Expression right;

  public Pair(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public Expression first() {
    return left;
  }

  public Expression second() {
    return right;
  }

  @Override
  public Expression reduce(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Pair(left.reduce(printer), right.reduce(printer));
    }
    return this;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Pair(left.reduceByValue(printer), right.reduceByValue(printer));
    }
    return this;
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    if (printer.isPrintBelowList()) {
      return new Pair(left.reduceByNeed(printer), right.reduceByNeed(printer));
    }
    return this;
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
    return new Pair(left.instantiate(var, exp), right.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(" + left + ", " + right + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair pair = (Pair) o;
    return left.equals(pair.left) && right.equals(pair.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }
}
