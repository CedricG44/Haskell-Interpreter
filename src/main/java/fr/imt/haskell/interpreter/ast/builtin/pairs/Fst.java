package fr.imt.haskell.interpreter.ast.builtin.pairs;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Pair;
import fr.imt.haskell.interpreter.ast.printer.Printer;

/** Pair first built-in functions. */
public class Fst extends UnaryExpression {

  public Fst(Expression exp) {
    super(exp);
  }

  @Override
  public Expression reduce(final Printer printer) {
    printer.incrementSteps();
    return ((Pair) exp.reduce(printer)).first();
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    printer.incrementSteps();
    return ((Pair) exp.reduceByValue(printer)).first();
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    printer.incrementSteps();
    return ((Pair) exp.reduceByNeed(printer)).first();
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Fst(this.exp.instantiate(var, exp));
  }

  @Override
  public String toString() {
    return "(fst " + exp + ")";
  }
}
