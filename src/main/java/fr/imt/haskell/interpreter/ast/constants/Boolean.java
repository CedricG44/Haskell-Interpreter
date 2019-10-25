package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Constant;

import java.util.Objects;

/** Boolean constants. */
public final class Boolean extends Constant {

  private final boolean value;

  public Boolean(boolean value) {
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value ? "True" : "False";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Boolean aBoolean = (Boolean) o;
    return value == aBoolean.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
