package fr.imt.haskell.interpreter.ast.builtin.pairs;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Pair;

/** Pair first built-in functions. */
public class Fst extends UnaryExpression {

  public Fst(Expression exp) {
    super(exp);
  }

  @Override
  public Expression reduce() {
    return ((Pair) exp.reduce()).first();
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
