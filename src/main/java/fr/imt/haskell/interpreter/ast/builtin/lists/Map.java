package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.constants.Number;

/** List map built-in functions. */
public final class Map extends UnaryExpression {

  private Lambda lambda;

  public Map(Expression expression, Lambda lambda) {
    super(expression);
    this.lambda = lambda;
  }

  @Override
  public Expression reduce() {
    System.out.println("[Map] Reduction step: " + this);
    return ((List) exp.reduce()).map(this.lambda);
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Map(exp.substitute(var, substitute), lambda);
  }

  @Override
  public String toString() {
    return "(map " + exp + ")";
  }
}
