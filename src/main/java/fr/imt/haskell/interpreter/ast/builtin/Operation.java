package fr.imt.haskell.interpreter.ast.builtin;

/** Arithmetic and logical operations. */
public enum Operation {
  DIVIDE("Divide", "/"),
  MINUS("Minus", "-"),
  PLUS("Plus", "+"),
  TIMES("Times", "*"),
  EQUAL("Equal", "=="),

  AND("And", "&&"),
  OR("Or", "||"),
  NOT("Not", "!");

  private final String name;
  private final String value;

  Operation(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }
}
