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
    final String oldExp = toString();
    final Expression newExp = ((Pair) exp.reduce(printer)).first();
    printer.changes.onNext(new javafx.util.Pair<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = ((Pair) exp.reduceByValue(printer)).first();
    printer.changes.onNext(new javafx.util.Pair<>(oldExp, newExp.toString()));
    return newExp;
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
