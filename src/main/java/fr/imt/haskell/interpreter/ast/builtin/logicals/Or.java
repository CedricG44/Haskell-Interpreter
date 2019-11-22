package fr.imt.haskell.interpreter.ast.builtin.logicals;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

/** Or built-in functions. */
public final class Or extends BinaryExpression {

  public Or(Expression expL, Expression expR) {
    super(expL, expR);
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        new Boolean(
            ((Boolean) expL.reduce(printer)).getValue()
                || ((Boolean) expR.reduce(printer)).getValue());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp =
        new Boolean(
            ((Boolean) expL.reduceByValue(printer)).getValue()
                || ((Boolean) expR.reduceByValue(printer)).getValue());
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Or(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "((|| " + expL + ") " + expR + ")";
  }
}
