package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

/** Conditional expressions. */
public final class ConditionalExpression extends Expression {

  private final Expression cond;
  private final Expression expL;
  private final Expression expR;

  public ConditionalExpression(Expression cond, Expression expL, Expression expR) {
    this.cond = cond;
    this.expL = expL;
    this.expR = expR;
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        ((Boolean) cond.reduce(printer)).getValue() ? expL.reduce(printer) : expR.reduce(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reducePrinter(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        ((Boolean) cond.reducePrinter(printer)).getValue()
            ? expL.reducePrinter(printer)
            : expR.reducePrinter(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        ((Boolean) cond.reduceByValue(printer)).getValue()
            ? expL.reduceByValue(printer)
            : expR.reduceByValue(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        ((Boolean) cond.reduceByNeed(printer)).getValue()
            ? expL.reduceByNeed(printer)
            : expR.reduceByNeed(printer);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new ConditionalExpression(
        cond.instantiate(var, exp), expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(if " + cond + " then " + expL + " else " + expR + ")";
  }
}
