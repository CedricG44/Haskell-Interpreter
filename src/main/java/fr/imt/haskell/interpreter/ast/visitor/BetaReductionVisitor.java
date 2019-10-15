package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;

public class BetaReductionVisitor implements Visitor {

  private Expression exp;

  public BetaReductionVisitor() {
    System.out.println("Starting Beta-Reduction...");
  }

  public Expression getExp() {
    return exp;
  }

  @Override
  public void visit(Application application) {}

  @Override
  public void visit(Lambda lambda) {}

  @Override
  public void visit(Variable variable) {}

  @Override
  public void visit(Constant constant) {}
}
