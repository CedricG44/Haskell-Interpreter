package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** List head built-in functions. */
public final class Head extends UnaryExpression {

  public Head(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    printer.incrementSteps();
    return ((List) exp.reduce(printer)).head();
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    printer.incrementSteps();
    return ((List) exp.reduceByValue(printer)).head();
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    printer.incrementSteps();
    return ((List) exp.reduceByNeed(printer)).head();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Head(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(head " + exp + ")";
  }
}
