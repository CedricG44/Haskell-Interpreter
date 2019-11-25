package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;
import java.util.Objects;

/** Applications. */
public final class Application extends Expression {

  private final Expression expL;
  private final Expression expR;

  public Application(Expression expL, Expression expR) {
    this.expL = expL;
    this.expR = expR;
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Lambda lambda = (Lambda) expL.reduce(printer);

    final Expression newExp = lambda.getExp().instantiate(lambda.getVar(), expR);
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));

    final Expression newReducedExp = newExp.reduce(printer);
    printer.changes.onNext(
        new AbstractMap.SimpleEntry<>(newExp.toString(), newReducedExp.toString()));
    return newReducedExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Lambda lambda = (Lambda) expL.reduceByValue(printer);

    final Expression newExp =
        lambda.getExp().instantiate(lambda.getVar(), expR.reduceByValue(printer));
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));

    final Expression newReducedExp = newExp.reduceByValue(printer);
    printer.changes.onNext(
        new AbstractMap.SimpleEntry<>(newExp.toString(), newReducedExp.toString()));
    return newReducedExp;
  }

  @Override
  public Expression reduceByNeed(Printer printer) {
    final String oldExp = toString();
    final Lambda lambda = (Lambda) expL.reduceByNeed(printer);

    final Expression newExp = lambda.getExp().instantiate(lambda.getVar(), new Indirection(expR));
    printer.changes.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));

    final Expression newReducedExp = newExp.reduceByNeed(printer);
    printer.changes.onNext(
        new AbstractMap.SimpleEntry<>(newExp.toString(), newReducedExp.toString()));
    return newReducedExp;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Application(expL.instantiate(var, exp), expR.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(" + expL + " " + expR + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Application that = (Application) o;
    return expL.equals(that.expL) && expR.equals(that.expR);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expL, expR);
  }
}
