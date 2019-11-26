package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** And built-in functions. */
public final class And extends BinaryExpression {

  public And(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Boolean) expL.reduce(printer)).getValue() && ((Boolean) expR.reduce(printer)).getValue());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Boolean) expL.reduceByValue(printer)).getValue()
            && ((Boolean) expR.reduceByValue(printer)).getValue());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Boolean) expL.reduceByNeed(printer)).getValue()
            && ((Boolean) expR.reduceByNeed(printer)).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new And(this.expL.instantiate(var, exp), this.expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((&& " + expL + ") " + expR + ")";
  }
}
