package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;

import java.util.Objects;

/** Plus constant. */
public final class Plus extends Constant {

  private final String value = "+";

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Plus plus = (Plus) o;
    return value.equals(plus.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
