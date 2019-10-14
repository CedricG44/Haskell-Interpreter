package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;

/** AST visitor interface. */
public interface Visitor {

  void visit(Expression expression);
}
