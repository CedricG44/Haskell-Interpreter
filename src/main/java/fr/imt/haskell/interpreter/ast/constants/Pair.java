package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

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
  public Expression reduce() {
    return new Pair(left.reduce(), right.reduce());
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
