package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

/** Not built-in functions. */
public final class Not extends UnaryExpression {

  public Not(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Boolean(!((Boolean) exp.reduce(printer)).getValue());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Boolean(!((Boolean) exp.reduceByValue(printer)).getValue());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
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
