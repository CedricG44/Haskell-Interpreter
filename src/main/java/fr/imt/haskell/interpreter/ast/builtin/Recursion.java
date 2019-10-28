package fr.imt.haskell.interpreter.ast.builtin;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Variable;

public class Recursion extends Expression {

  private final Expression h;

  public Recursion(Expression h) {
    this.h = h;
  }

  @Override
  public Expression reduce() {
    return new Application(h, new Application(this, h));
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    System.out.println("pk on t'appelle pas frero");
    return new Recursion(h.substitute(var, substitute));
  }

  @Override
  public String toString() {
    return "Recursion{" + "h=" + h + '}';
  }
}
