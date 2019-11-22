package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;
import fr.imt.haskell.interpreter.ast.Expression;

import java.util.Objects;

/** Number constants. */
public final class Number extends Constant {

  private final int value;

  public Number(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Number number = (Number) o;
    return value == number.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
