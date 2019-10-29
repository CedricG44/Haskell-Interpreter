package fr.imt.haskell.interpreter.ast.builtin.pairs;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Pair;

/** Pair second built-in functions. */
public class Snd extends UnaryExpression {

  public Snd(Expression exp) {
    super(exp);
  }

  @Override
  public Expression reduce() {
    return ((Pair) exp.reduce()).second();
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    return new Snd(exp.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "(snd " + exp + ")";
  }
}
