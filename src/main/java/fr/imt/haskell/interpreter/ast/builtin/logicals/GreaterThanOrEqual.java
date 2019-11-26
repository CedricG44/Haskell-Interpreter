package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Greater than or equal built-in functions. */
public class GreaterThanOrEqual extends BinaryExpression {

  public GreaterThanOrEqual(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Number) expL.reduce(printer)).getValue() >= ((Number) expR.reduce(printer)).getValue());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Number) expL.reduceByValue(printer)).getValue()
            >= ((Number) expR.reduceByValue(printer)).getValue());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(
        ((Number) expL.reduceByNeed(printer)).getValue()
            >= ((Number) expR.reduceByNeed(printer)).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new GreaterThanOrEqual(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((>= " + expL + ") " + expR + ")";
  }
}
