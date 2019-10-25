package fr.imt.haskell.interpreter.ast.constants;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

/** Minus unary expression */
public final class Minus extends UnaryExpression {

  public Expression expression;

  public Minus(Expression expression){
    this.expression = expression;
  }

  @Override
  public String toString() {
    return "(- " + expression.toString() + ")";
  }

  @Override
  public boolean isReducible() {
    return expression.isReducible();
  }

  @Override
  public Expression reduce() {
    return isReducible() ? expression.reduce() : this;
  }

  @Override
  public Expression substitute(Variable var, Expression substitute) {
    Expression exp = this.expression.substitute(var, substitute);
    if(! (exp instanceof Number)){
      return new Minus(expression);
    }
    return new Number(- ((Number) exp).getValue());
  }
}
