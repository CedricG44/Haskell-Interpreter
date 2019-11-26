package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Equal built-in functions. */
public final class Equal extends BinaryExpression {

  public Equal(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce(final Printer printer) {
    return new Boolean(expL.reduce(printer).equals(expR.reduce(printer)));
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return new Boolean(expL.reduceByValue(printer).equals(expR.reduceByValue(printer)));
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return new Boolean(expL.reduceByNeed(printer).equals(expR.reduceByNeed(printer)));
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Equal(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((== " + expL + ") " + expR + ")";
  }
}
