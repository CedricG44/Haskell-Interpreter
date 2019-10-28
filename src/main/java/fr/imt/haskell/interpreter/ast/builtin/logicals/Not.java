package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

public final class Not extends UnaryExpression {

  public Not(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce() {
    return new Boolean(!((Boolean) exp.reduce()).getValue());
  }

  @Override
  public Expression substitute(Variable var, Expression substitute) {
    return new Not(exp.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "(not " + exp + ")";
  }
}
