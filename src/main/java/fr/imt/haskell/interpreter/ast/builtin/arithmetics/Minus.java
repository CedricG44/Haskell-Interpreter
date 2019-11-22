package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.printer.Printer;
import javafx.util.Pair;

/** Minus built-in functions. */
public final class Minus extends UnaryExpression {

  public Minus(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Number(-((Number) exp.reduce(printer)).getValue());
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Number(-((Number) exp.reduceByValue(printer)).getValue());
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reducePrinter(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = new Number(-((Number) exp.reducePrinter(printer)).getValue());
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
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
