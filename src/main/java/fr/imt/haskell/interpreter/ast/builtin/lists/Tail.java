package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;

/** List tail built-in functions. */
public final class Tail extends UnaryExpression {

  public Tail(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Tail] Reduction step: " + this);
    return ((List) exp.reduce()).tail();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Tail(exp.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "(tail " + exp + ")";
  }
}
