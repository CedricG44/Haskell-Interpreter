package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;

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
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Head(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(head " + exp + ")";
  }
}
