package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Not built-in functions. */
public final class Not extends UnaryExpression {

  public Not(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    return new Boolean(!((Boolean) exp.reduce(printer)).getValue());
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return new Boolean(!((Boolean) exp.reduceByValue(printer)).getValue());
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return new Boolean(!((Boolean) exp.reduceByNeed(printer)).getValue());
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Not(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(not " + exp + ")";
  }
}
