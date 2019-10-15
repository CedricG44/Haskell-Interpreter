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
  public void visit(Application application) {
    if (application.getExpL().isLambda() && !application.getExpR().isApplication()) {
      Lambda lambda = (Lambda) application.getExpL();
      exp = lambda.reduct(lambda.getVar(), application.getExpR());
    } else {
      application.getExpL().accept(this);
      application.getExpR().accept(this);
    }
  }

  @Override
  public void visit(final Lambda lambda) {
    exp = lambda;
  }

  @Override
  public void visit(final Variable variable) {
    exp = variable;
  }

  @Override
  public void visit(final Constant constant) {
    exp = constant;
  }
}
