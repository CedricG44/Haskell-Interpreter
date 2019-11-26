package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** List null built-in functions. */
public final class Null extends UnaryExpression {

  public Null(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(((List) exp.reduce(printer)).isEmpty());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(((List) exp.reduceByValue(printer)).isEmpty());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    printer.incrementSteps();
    return new Boolean(((List) exp.reduceByNeed(printer)).isEmpty());
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
