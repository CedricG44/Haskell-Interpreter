package fr.imt.haskell.interpreter.ast.builtin.pairs;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Pair;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Pair second built-in functions. */
public class Snd extends UnaryExpression {

  public Snd(Expression exp) {
    super(exp);
  }

  @Override
  public Expression reduce(final Printer printer) {
    return ((Pair) exp.reduce(printer)).second();
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    return ((Pair) exp.reduceByValue(printer)).first();
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    return ((Pair) exp.reduceByNeed(printer)).second();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Snd(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(snd " + exp + ")";
  }
}
