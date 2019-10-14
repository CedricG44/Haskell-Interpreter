package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;

/** Beta-Reduction implementation using AST visitor. */
public class BetaReductionVisitor implements Visitor {

  @Override
  public void visit(Expression expression) {
    System.out.println("Visited :" + expression);
  }
}
