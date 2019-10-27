package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;

public interface BuiltInFunction {

  Expression eval();
}
