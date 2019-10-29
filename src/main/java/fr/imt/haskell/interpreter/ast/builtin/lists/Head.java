package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;

/** List head built-in functions. */
public final class Head extends UnaryExpression {

  public Head(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Head] Reduction step: " + this);
    return ((List) exp.reduce()).head();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Head(exp.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "(head " + exp + ")";
  }
}
