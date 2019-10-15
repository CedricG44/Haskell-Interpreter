package fr.imt.haskell.interpreter.ast.reductor;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;

/** Beta-reductor. */
public class Reductor {

  public static Expression substitute(Expression toSubsitute, Variable var, Expression substitute) {
    if (toSubsitute.isConstant()) {
      return toSubsitute;
    } else if (toSubsitute.isVariable()) {
      if (var.equals(toSubsitute)) {
        return substitute;
      }
    } else if (toSubsitute.isApplication()) {
      Application app = (Application) toSubsitute;
      return new Application(
          substitute(app.getExpL(), var, substitute), substitute(app.getExpR(), var, substitute));
    } else if (toSubsitute.isLambda()) {
      Lambda lambda = (Lambda) toSubsitute;
      return new Lambda(var, substitute(lambda.getExp(), lambda.getVar(), substitute));
    }
    return toSubsitute;
  }
}
