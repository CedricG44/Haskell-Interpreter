package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.constants.Boolean;

/** Conditional expressions. */
public final class ConditionalExpression extends Expression {

  private final Expression cond;
  private final Expression expL;
  private final Expression expR;

  public ConditionalExpression(Expression cond, Expression expL, Expression expR) {
    this.cond = cond;
    this.expL = expL;
    this.expR = expR;
  }

  @Override
  public Expression reduce() {
    System.out.println("[ConditionalExpression] Reduction step: " + this);
    return ((Boolean) cond.reduce()).getValue() ? expL.reduce() : expR.reduce();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new ConditionalExpression(
        cond.instantiate(var, exp),
        expL.instantiate(var, exp),
        expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(if " + cond + " then " + expL + " else " + expR + ")";
  }
}
