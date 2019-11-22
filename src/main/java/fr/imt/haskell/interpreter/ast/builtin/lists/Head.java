package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.printer.Printer;
import javafx.util.Pair;

/** List head built-in functions. */
public final class Head extends UnaryExpression {

  public Head(Expression expression) {
    super(expression);
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = ((List) exp.reduce(printer)).head();
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = ((List) exp.reduceByValue(printer)).head();
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reducePrinter(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = ((List) exp.reducePrinter(printer)).head();
    printer.changes.onNext(new Pair<>(oldExp, newExp.toString()));
    return newExp;
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
