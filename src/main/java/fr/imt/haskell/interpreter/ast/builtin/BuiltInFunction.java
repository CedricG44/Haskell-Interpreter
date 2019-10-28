package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

public abstract class BuiltInFunction extends Expression {

  public abstract Expression eval();
}
