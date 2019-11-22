package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

/** List null built-in functions. */
public final class Null extends UnaryExpression {

  public Null(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Boolean(((List) exp.reduce(printer)).isEmpty());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Boolean(((List) exp.reduceByValue(printer)).isEmpty());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reducePrinter(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Boolean(((List) exp.reduceByValue(printer)).isEmpty());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
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
