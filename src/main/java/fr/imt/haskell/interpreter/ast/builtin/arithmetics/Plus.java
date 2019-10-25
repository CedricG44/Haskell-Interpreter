package fr.imt.haskell.interpreter.ast.builtin.arithmetics;

import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.builtin.BinaryExpression;
import fr.imt.haskell.interpreter.ast.constants.Number;

import static fr.imt.haskell.interpreter.ast.builtin.Operation.PLUS;

/** Plus built-in functions. */
public final class Plus extends BinaryExpression {

  public Plus(Expression expL, Expression expR) {
    super(PLUS, expL, expR);
  }

  @Override
  public Expression eval() {
    if (!(expL instanceof Number && expR instanceof Number)) {
      return new Plus(expL, expR);
    }
    return new Number(((Number) expL).getValue() + ((Number) expR).getValue());
  }

  @Override
  public String toString() {
    return "((+ " + expL + ") " + expR + ")";
  }
}
