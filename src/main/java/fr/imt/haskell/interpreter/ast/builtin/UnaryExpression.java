package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Minus;
import fr.imt.haskell.interpreter.ast.builtin.logicals.Not;

/** Unary expressions. */
public abstract class UnaryExpression extends Expression {

  private final Operation op;
  protected final Expression exp;

  public UnaryExpression(Operation op, Expression exp) {
    this.op = op;
    this.exp = exp;
  }

  @Override
  public boolean isReducible() {
    return exp.isReducible();
  }

  @Override
  public Expression reduce() {
    System.out.println("[" + op.getName() + "] Reduction step: " + this);
    return isReducible() ? exp.reduce() : eval();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    switch (op) {
      case MINUS:
        return new Minus(exp.substitute(var, substitute)).eval();
      case NOT:
        return new Not(exp.substitute(var, substitute)).eval();
      default:
        return eval();
    }
  }

  public abstract Expression eval();

  public Expression getExp() {
    return exp;
  }
}
