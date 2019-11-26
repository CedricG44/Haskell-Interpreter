package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** List lentgth built-in functions. */
public final class Length extends UnaryExpression {

  public Length(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    return new Number(((List) exp.reduce(printer)).length());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return new Number(((List) exp.reduceByValue(printer)).length());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return new Number(((List) exp.reduceByNeed(printer)).length());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Length(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(length " + exp + ")";
  }
}
