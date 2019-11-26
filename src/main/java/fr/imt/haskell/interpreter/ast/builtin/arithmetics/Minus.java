package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Minus built-in functions. */
public final class Minus extends UnaryExpression {

  public Minus(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    return new Number(-((Number) exp.reduce(printer)).getValue());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return new Number(-((Number) exp.reduceByValue(printer)).getValue());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return new Number(-((Number) exp.reduceByNeed(printer)).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Minus(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(- " + exp + ")";
  }
}
