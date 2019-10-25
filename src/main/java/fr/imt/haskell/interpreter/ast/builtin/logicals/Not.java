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
  public Expression substitute(final Variable var, final Expression substitute) {
    final Expression exp = this.expression.substitute(var, substitute);
    if (!(exp instanceof Boolean)) {
      return new Not(exp);
    }
    return new Boolean(!((Boolean) exp).getValue());
  }

  @Override
  public String toString() {
    return "(! " + expression + ")";
  }
}
