package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.List;

/** List null built-in functions. */
public final class Null extends UnaryExpression {

  public Null(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    System.out.println("[Null] Reduction step: " + this);
    return new Boolean(((List) exp.reduce()).isEmpty());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Null(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(null " + exp + ")";
  }
}
