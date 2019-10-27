package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Divide;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Equal;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Plus;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.Times;
import fr.imt.haskell.interpreter.ast.builtin.logicals.And;
import fr.imt.haskell.interpreter.ast.builtin.logicals.Or;

/** Binary expressions. */
public abstract class BinaryExpression extends Application implements BuiltInFunction {

  private final Operation op;

  public BinaryExpression(Operation op, Expression expL, Expression expR) {
    super(expL, expR);
    this.op = op;
  }

  @Override
  public boolean isReducible() {
    return expL.isReducible() || expR.isReducible();
  }

  @Override
  public Expression reduce() {
    System.out.println("[" + op.getName() + "] Reduction step: " + this);
    final Expression expL =
        this.expL.isReducible() || (this.expL instanceof BuiltInFunction)
            ? this.expL.reduce()
            : this.expL;
    final Expression expR =
        this.expR.isReducible() || (this.expR instanceof BuiltInFunction)
            ? this.expR.reduce()
            : this.expR;

    switch (op) {
      case DIVIDE:
        return new Divide(expL, expR).eval();
      case PLUS:
        return new Plus(expL, expR).eval();
      case TIMES:
        return new Times(expL, expR).eval();
      case AND:
        return new And(expL, expR).eval();
      case OR:
        return new Or(expL, expR).eval();
      case EQUAL:
        return new Equal(expL, expR).eval();
      default:
        return eval();
    }
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    switch (op) {
      case DIVIDE:
        return new Divide(expL.substitute(var, substitute), expR.substitute(var, substitute))
            .eval();
      case PLUS:
        return new Plus(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();
      case TIMES:
        return new Times(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();
      case AND:
        return new And(this.expL.substitute(var, substitute), this.expR.substitute(var, substitute))
            .eval();
      case OR:
        return new Or(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();
      case EQUAL:
        return new Equal(expL.substitute(var, substitute), expR.substitute(var, substitute)).eval();
      default:
        return eval();
    }
  }
}
