package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;

/** AST visitor interface. */
public interface Visitor {

  void visit(final Application application);

  void visit(final Lambda lambda);

  void visit(final Variable variable);

  void visit(final Constant constant);
}
