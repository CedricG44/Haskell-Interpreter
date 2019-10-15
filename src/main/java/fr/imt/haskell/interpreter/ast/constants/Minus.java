package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;

import java.util.Objects;

/** Minus constant. */
public final class Minus extends Constant {

  private final String value = "-";

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Minus minus = (Minus) o;
    return value.equals(minus.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
